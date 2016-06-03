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
            MessageRepository messageRepository,
            MatchDayRepository matchDayRepository,
            BonusChoiceRepository bonusChoiceRepository,
            BonusRepository bonusRepository
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
        User userFrank = new User("f.bosma@vpro.nl", Role.ADMIN, "Frank Bosma", "Bosman", userGroupDigitaal);
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

        MatchDay preliminary = new MatchDay("Voorrondes");
        MatchDay eights = new MatchDay("Achtste Finales");
        MatchDay quarter = new MatchDay("Kwartfinales");

        matchDayRepository.save(Arrays.asList(
                preliminary,
                eights,
                quarter
        ));

        // Matches

        Date now = new Date();
        Date lastWeek = Date.from(now.toInstant().minus(7, ChronoUnit.DAYS));
        Date midLastWeek = Date.from(now.toInstant().minus(4, ChronoUnit.DAYS));
        Date nextWeek = Date.from(now.toInstant().plus(7, ChronoUnit.DAYS));
        Date almost = Date.from(now.toInstant().plus(2, ChronoUnit.HOURS));

        Match matchFinished1 = new Match("Zwitserland", "Noord-Ierland", lastWeek, new MatchResult(3, 1), preliminary );
        Match matchFinished2 = new Match("België", "Engeland", lastWeek, new MatchResult(5, 4), preliminary);
        Match matchFinished3 = new Match("Frankrijk", "Albanië", lastWeek, new MatchResult(2, 0), preliminary);
        Match matchFinished4 = new Match("Oostenrijk", "Portugal", midLastWeek, new MatchResult(1, 2), eights);

        Match matchUnfinished1 = new Match("Frankrijk", "Duitsland", now, null, eights);
        Match matchUnfinished2 = new Match("Spanje", "Engeland", now, null, eights);

        Match matchFuture1 = new Match("Portugal", "België", almost, null, quarter);
        Match matchFuture2 = new Match("Engeland", "Oostenrijk", nextWeek, null, quarter);

        matchRepository.save(Arrays.asList(
                matchFinished1,
                matchFinished2,
                matchFinished3,
                matchFinished4,
                matchUnfinished1,
                matchUnfinished2,
                matchFuture1,
                matchFuture2
        ));

        // Bonus stuff

        BonusChoice france = new BonusChoice("Frankrijk", BonusCategory.COUNTRY);
        BonusChoice portugal = new BonusChoice("Portugal", BonusCategory.COUNTRY);
        BonusChoice ronaldo = new BonusChoice("Ronaldo", BonusCategory.PLAYER);
        BonusChoice ibra = new BonusChoice("Ibrahimovic", BonusCategory.PLAYER);

        bonusChoiceRepository.save(Arrays.asList(
                france,
                portugal,
                ronaldo,
                ibra
        ));

        Bonus topScorert = new Bonus( "Wie wordt de topscorer van deze ronde?", BonusCategory.PLAYER, lastWeek, 3, ronaldo, preliminary );
        Bonus topScorert2 = new Bonus( "Wie wordt de topscorer van deze ronde?", BonusCategory.PLAYER, now, 3, null, eights );
        Bonus topScorert3 = new Bonus( "Wie wordt de topscorer van deze ronde?", BonusCategory.PLAYER, nextWeek, 3, null, quarter );

        bonusRepository.save(Arrays.asList(
                topScorert,
                topScorert2,
                topScorert3
        ));

        // Predictions

        predictionRepository.save(Arrays.asList(
                new Prediction(userNils, matchFinished1, new MatchResult(1, 0)),
                new Prediction(userNils, matchFinished2, new MatchResult(2, 2), true),
                new Prediction(userNils, topScorert, ronaldo, true),
                new Prediction(userFrank, matchFinished2, new MatchResult(2, 1), true),
                new Prediction(userFrank, matchUnfinished1, new MatchResult(2, 1), true),
                new Prediction(userFrank, topScorert, ronaldo, false),
                new Prediction(userFrank, topScorert2, ibra),
                new Prediction(userTimo, matchFinished2, new MatchResult(0, 2)),
                new Prediction(userTimo, matchFinished4, new MatchResult(0, 2), true),
                new Prediction(userTimo, topScorert, ronaldo, true)
        ));

        // Messages

        messageRepository.save(Arrays.asList(
                new Message("/predictions", "Hier een melding voor invullers"),
                new Message("/user", "Hier een melding voor je profiel"),
                new Message("/ranking", "Hier een melding voor de ranking")
        ));
    }
}
