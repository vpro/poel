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
    public void ranking() {

        User jan = new User("Jan", Role.USER, "Jan", "voetbalJan");
        User piet = new User("Piet", Role.ADMIN, "Piet", "voetbalPiet");
        User klaas = new User("Klaas", Role.USER, "Klaas", "voetbalKlaas");
        User henk = new User("Henk", Role.ADMIN, "Henk", "voetbalHenk");

        UserService userService = mock(UserService.class);
        UserGroupService userGroupService = mock(UserGroupService.class);
        when(userService.getAllUsers()).thenReturn(Arrays.asList(jan, piet, klaas, henk));

        ScoreService scoreService = mock(ScoreService.class);
        when(scoreService.getScore(jan)).thenReturn(7);
        when(scoreService.getScore(piet)).thenReturn(5);
        when(scoreService.getScore(klaas)).thenReturn(5);
        when(scoreService.getScore(henk)).thenReturn(0);

        RankingService rankingService = new RankingServiceImpl(userService, userGroupService, scoreService);

        List<RankingEntry<User, Integer>> ranking = rankingService.getUserRanking();

        assertThat(ranking).containsExactly(
                new RankingEntry<>(1, jan, 7),
                new RankingEntry<>(2, piet, 5),
                new RankingEntry<>(2, klaas, 5),
                new RankingEntry<>(4, henk, 0)
        );
    }
}