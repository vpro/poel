package nl.vpro.poel.service;

import nl.vpro.poel.domain.Team;
import nl.vpro.poel.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;

        if (teamRepository.count() == 0) {
            List<Team> defaultTeams = Arrays.asList(
                    new Team("Frankrijk"),
                    new Team("Duitsland"),
                    new Team("Spanje"),
                    new Team("Engeland"),
                    new Team("Portugal"),
                    new Team("België"),
                    new Team("Italië"),
                    new Team("Rusland"),
                    new Team("Zwitserland"),
                    new Team("Oostenrijk"),
                    new Team("Kroatië"),
                    new Team("Oekraïne"),
                    new Team("Tsjechië"),
                    new Team("Zweden"),
                    new Team("Polen"),
                    new Team("Roemenië"),
                    new Team("Slowakije"),
                    new Team("Hongarije"),
                    new Team("Turkije"),
                    new Team("Ierland"),
                    new Team("IJsland"),
                    new Team("Wales"),
                    new Team("Albanië"),
                    new Team("Noord-Ierland")
            );
            teamRepository.save(defaultTeams);
        }
    }

    @Override
    public Optional<Team> getTeamById(Long id) {
        return Optional.ofNullable(teamRepository.findOne(id));
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
