package nl.vpro.poel.service;

import nl.vpro.poel.domain.*;
import nl.vpro.poel.dto.RankingEntry;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RankingServiceImplTest {

    @Test
    public void getRankingEntry() throws Exception {

        // TODO: If the score for a user was mockable, this could get a whole lot shorter

        User jan = new User("Jan", Role.USER, "Jan", "voetbalJan");
        User piet = new User("Piet", Role.ADMIN, "Piet", "voetbalPiet");
        User klaas = new User("Klaas", Role.USER, "Klaas", "voetbalKlaas");
        User henk = new User("Henk", Role.ADMIN, "Henk", "voetbalHenk");

        Match A_B = new Match("A", "B", new Date(), new MatchResult(1, 0));
        Match A_C = new Match("A", "C", new Date(), new MatchResult(2, 2));
        Match B_C = new Match("B", "C", new Date(), new MatchResult(4, 2));

        UserService userService = mock(UserService.class);
        UserGroupService userGroupService = mock(UserGroupService.class);
        when(userService.getAllUsers()).thenReturn(Arrays.asList(jan, piet, klaas, henk));

        PredictionService predictionService = mock(PredictionService.class);

        when(predictionService.getPredictions(jan)).thenReturn(Arrays.asList(
                new Prediction(jan, A_B, new MatchResult(2, 0)),
                new Prediction(jan, A_C, new MatchResult(1, 1)),
                new Prediction(jan, B_C, new MatchResult(4, 2))
        ));
        when(predictionService.getPredictions(piet)).thenReturn(Arrays.asList(
                new Prediction(piet, A_B, new MatchResult(1, 0)),
                new Prediction(piet, A_C, new MatchResult(2, 1)),
                new Prediction(piet, B_C, new MatchResult(3, 2))

        ));
        when(predictionService.getPredictions(klaas)).thenReturn(Arrays.asList(
                new Prediction(klaas, A_B, new MatchResult(1, 0)),
                new Prediction(klaas, A_C, new MatchResult(2, 1)),
                new Prediction(klaas, B_C, new MatchResult(3, 2))
        ));

        RankingService rankingService = new RankingServiceImpl(userService, userGroupService, predictionService);

        List<RankingEntry> ranking = rankingService.getRanking();

        assertThat(ranking).containsExactly(
                new RankingEntry(1, jan, 7),
                new RankingEntry(2, piet, 5),
                new RankingEntry(2, klaas, 5),
                new RankingEntry(4, henk, 0)
        );
    }
}