package nl.vpro.poel.configuration;

import nl.vpro.poel.domain.*;
import nl.vpro.poel.repository.*;
import nl.vpro.poel.service.UserGroupService;
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

    private final UserGroupRepository userGroupRepository;
    private final UserGroupService userGroupService;
    private final MatchRepository matchRepository;
    private final PredictionRepository predictionRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    DummyDataLoader(
            UserGroupRepository userGroupRepository,
            UserGroupService userGroupService,
            MatchRepository matchRepository,
            PredictionRepository predictionRepository,
            MessageRepository messageRepository,
            UserRepository userRepository
    ) {
        this.userGroupRepository = userGroupRepository;
        this.userGroupService = userGroupService;
        this.matchRepository = matchRepository;
        this.predictionRepository = predictionRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;

        groups();
        adminUsers();
        matches();
        predictions();
        messages();
    }

    private void adminUsers() {

        UserGroup userGroupDigitaal = userGroupService.findByName("Digitaal").orElse(null);

        List<User> admins = Arrays.asList(
                new User("n.breunese@vpro.nl", Role.ADMIN, "Nils Breunese", "Van Breunhorst", userGroupDigitaal),
                new User("f.bosma@vpro.nl", Role.ADMIN, "Frank Bosma", "Bosmatic", userGroupDigitaal),
                new User("t.klok@vpro.nl", Role.ADMIN, "Timo Klok", " Ibratimovich", userGroupDigitaal),
                new User("d.pronk@vpro.nl", Role.ADMIN, "David Pronk", "van Pronkhorst", userGroupDigitaal),
                new User("f.hermsen@vpro.nl", Role.ADMIN, "Fred Hermsen", "The Herminator", userGroupDigitaal),
                new User("l.de.knijff@gmail.com", Role.ADMIN, "Laurens de Knijff", "Databeest", userGroupDigitaal),
                new User("l.schipperheyn@vpro.nl", Role.ADMIN, "Luuk Schipperheyn", "Luuk de Jonguh", userGroupDigitaal)
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

    private void predictions() {
        User user1 = userRepository.findOne(1L);
        predictionRepository.save(Arrays.asList(
                new Prediction(user1, matchRepository.findOne(1L), new MatchResult(1, 0)),
                new Prediction(user1, matchRepository.findOne(2L), new MatchResult(2, 2), true)
        ));

        User user2 = userRepository.findOne(2L);
        predictionRepository.save(Arrays.asList(
                new Prediction(user2, matchRepository.findOne(2L), new MatchResult(2, 1), true),
                new Prediction(user2, matchRepository.findOne(3L), new MatchResult(0, 2))
        ));
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

        List<UserGroup> defaultUserGroups = Arrays.asList(
                new UserGroup("Digitaal"),
                new UserGroup("Voetbalvrouwen")
        );
        userGroupRepository.save(defaultUserGroups);
    }
}
