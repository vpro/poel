package nl.vpro.poel.configuration;

import nl.vpro.poel.domain.*;
import nl.vpro.poel.repository.GroupRepository;
import nl.vpro.poel.repository.MatchRepository;
import nl.vpro.poel.repository.MessageRepository;
import nl.vpro.poel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Profile("development")
public class DummyDataLoader {

    private final GroupRepository groupRepository;

    private final MatchRepository matchRepository;

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    @Autowired
    DummyDataLoader(GroupRepository groupRepository, MatchRepository matchRepository, MessageRepository messageRepository, UserRepository userRepository) {

        this.groupRepository = groupRepository;
        this.matchRepository = matchRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;

        adminUsers();
        matches();
        messages();
        groups();
    }

    private void adminUsers() {
        List<User> admins = Arrays.asList(
                new User("n.breunese@vpro.nl", Role.ADMIN, "Nils Breunese", "Van Breunhorst"),
                new User("f.bosma@vpro.nl", Role.ADMIN, "Frank Bosma", "Bosmatic"),
                new User("t.klok@vpro.nl", Role.ADMIN, "Timo Klok", " Ibratimovich"),
                new User("d.pronk@vpro.nl", Role.ADMIN, "David Pronk", "van Pronkhorst"),
                new User("f.hermsen@vpro.nl", Role.ADMIN, "Fred Hermsen", "The Herminator"),
                new User("l.de.knijff@gmail.com", Role.ADMIN, "Laurens de Knijff", "Databeest")
        );
        userRepository.save(admins);
    }

    private void matches() {
        Date now = new Date();
        Date lastWeek = Date.from(now.toInstant().minus(7, ChronoUnit.DAYS));
        Date nextWeek = Date.from(now.toInstant().plus(7, ChronoUnit.DAYS));
        Date almost = Date.from(now.toInstant().plus(2, ChronoUnit.HOURS));

        List<Match> defaultMatches = Arrays.asList(

                // Finished
                new Match("Zwitserland", "Noord-Ierland", lastWeek, new MatchResult(3, 1)),
                new Match("België", "Engeland", lastWeek, new MatchResult(5, 4)),

                // Unfinished
                new Match("Frankrijk", "Duitsland", now),
                new Match("Spanje", "Engeland", now),

                // Future
                new Match("Portugal", "België", almost),
                new Match("Engeland", "Oostenrijk", nextWeek)
        );
        matchRepository.save(defaultMatches);
    }

    private void messages() {

        List<Message> defaultMessages = Arrays.asList(

                new Message("/predictions", "Hier een melding voor invullers"),
                new Message("/user", "Hier een melding voor je profiel"),
                new Message("/ranking", "Hier een melding voor de ranking")
        );
        messageRepository.save(defaultMessages);
    }

    private void groups() {

        List<Group> defaultGroups = Arrays.asList(

                new Group("Digitaal"),
                new Group("Voetbalvrouwen")
        );
        groupRepository.save(defaultGroups);
    }
}
