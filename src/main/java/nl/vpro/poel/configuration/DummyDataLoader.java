package nl.vpro.poel.configuration;

import nl.vpro.poel.domain.*;
import nl.vpro.poel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;

@Component
@Profile("development")
public class DummyDataLoader {

    @Autowired
    DummyDataLoader(
            UserGroupRepository userGroupRepository,
            UserRepository userRepository,
            MatchRepository matchRepository,
            PredictionRepository predictionRepository,
            MessageRepository messageRepository
    ) {
        // User groups

        UserGroup userGroupDigitaal = new UserGroup("Digitaal");
        UserGroup userGroupGids = new UserGroup("Gids");
        UserGroup userGroupVoetbalvrouwen = new UserGroup("Voetbalvrouwen");

        userGroupRepository.save(Arrays.asList(
                userGroupDigitaal,
                userGroupGids,
                userGroupVoetbalvrouwen
        ));

        // Users

        User userNils = new User("n.breunese@vpro.nl", Role.ADMIN, "Nils Breunese", "Van Breunhorst", userGroupDigitaal);
        User userFrank = new User("f.bosma@vpro.nl", Role.ADMIN, "Frank Bosma", "Bosmatic", userGroupDigitaal);
        User userTimo = new User("t.klok@vpro.nl", Role.ADMIN, "Timo Klok", " Ibratimovich", userGroupDigitaal);
        User userDavid = new User("d.pronk@vpro.nl", Role.ADMIN, "David Pronk", "van Pronkhorst", userGroupDigitaal);
        User userFred = new User("f.hermsen@vpro.nl", Role.ADMIN, "Fred Hermsen", "The Herminator", userGroupGids);
        User userLaurens = new User("l.de.knijff@gmail.com", Role.ADMIN, "Laurens de Knijff", "Databeest", userGroupVoetbalvrouwen);
        User userLuuk = new User("l.schipperheyn@vpro.nl", Role.ADMIN, "Luuk Schipperheyn", "Luuk de Jonguh", userGroupDigitaal);

        userRepository.save(Arrays.asList(
                userNils,
                userFrank,
                userTimo,
                userDavid,
                userFred,
                userLaurens,
                userLuuk
        ));

        // Matches

        Date now = new Date();
        Date lastWeek = Date.from(now.toInstant().minus(7, ChronoUnit.DAYS));
        Date nextWeek = Date.from(now.toInstant().plus(7, ChronoUnit.DAYS));
        Date almost = Date.from(now.toInstant().plus(2, ChronoUnit.HOURS));

        Match matchFinished1 = new Match("Zwitserland", "Noord-Ierland", lastWeek, new MatchResult(3, 1));
        Match matchFinished2 = new Match("België", "Engeland", lastWeek, new MatchResult(5, 4));

        Match matchUnfinished1 = new Match("Frankrijk", "Duitsland", now);
        Match matchUnfinished2 = new Match("Spanje", "Engeland", now);

        Match matchFuture1 = new Match("Portugal", "België", almost);
        Match matchFuture2 = new Match("Engeland", "Oostenrijk", nextWeek);

        matchRepository.save(Arrays.asList(
                matchFinished1,
                matchFinished2,
                matchUnfinished1,
                matchUnfinished2,
                matchFuture1,
                matchFuture2
        ));

        // Predictions

        predictionRepository.save(Arrays.asList(
                new Prediction(userNils, matchFinished1, new MatchResult(1, 0)),
                new Prediction(userNils, matchFinished2, new MatchResult(2, 2), true),
                new Prediction(userFrank, matchUnfinished1, new MatchResult(2, 1), true),
                new Prediction(userTimo, matchFinished2, new MatchResult(0, 2))
        ));

        // Messages

        messageRepository.save(Arrays.asList(
                new Message("/predictions", "Hier een melding voor invullers"),
                new Message("/user", "Hier een melding voor je profiel"),
                new Message("/ranking", "Hier een melding voor de ranking")
        ));
    }
}
