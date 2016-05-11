package nl.vpro.poel.service;

import nl.vpro.poel.domain.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    Optional<Team> getTeamById(Long id);

    Optional<Team> getTeamByName(String name);

    List<Team> getAllTeams();
}
