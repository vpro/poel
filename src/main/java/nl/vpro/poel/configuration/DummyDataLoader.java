package nl.vpro.poel.configuration;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.MatchResult;
import nl.vpro.poel.domain.Role;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.repository.MatchRepository;
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

    private final MatchRepository matchRepository;

    private final UserRepository userRepository;

    @Autowired
    DummyDataLoader(MatchRepository matchRepository, UserRepository userRepository) {
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;

        adminUsers();
        matches();
    }

    private void adminUsers() {
        List<User> admins = Arrays.asList(
                new User("n.breunese@vpro.nl", Role.ADMIN, "Van Breunhorst"),
                new User("f.bosma@vpro.nl", Role.ADMIN, "Frank Bosma"),
                new User("t.klok@vpro.nl", Role.ADMIN, "Timo Klok"),
                new User("d.pronk@vpro.nl", Role.ADMIN, "David Pronk")
        );
        userRepository.save(admins);
    }

    private void matches() {
        Date now = new Date();
        Date lastWeek = Date.from(now.toInstant().minus(7, ChronoUnit.DAYS));
        Date nextWeek = Date.from(now.toInstant().plus(7, ChronoUnit.DAYS));

        List<Match> defaultMatches = Arrays.asList(

                // Finished
                new Match("ch", "fr", lastWeek, new MatchResult(3, 1)),
                new Match("be", "gb-eng", lastWeek, new MatchResult(5, 4)),

                // Unfinished
                new Match("fr", "de", now),
                new Match("es", "gb-eng", now),

                // Future
                new Match("pt", "be", nextWeek),
                new Match("gb-eng", "at", nextWeek)
        );
        matchRepository.save(defaultMatches);
    }
}
