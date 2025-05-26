package lt.vu.psk.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lt.vu.psk.dto.TeamDTO;
import lt.vu.psk.entity.Player;
import lt.vu.psk.entity.Team;
import lt.vu.psk.entity.Tournament;
import lt.vu.psk.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public void createTeam(TeamDTO teamDto) {
        var team = Team.builder()
                .name(teamDto.getName())
                .country(teamDto.getCountry())
                .city(teamDto.getCity())
                .build();
        teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team with ID '" + teamId + "' not found."));
    }

    public List<Player> getTeamPlayers(Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        if (team != null) {
            return team.getPlayers();
        }
        return null;
    }

    public List<Tournament> getTeamTournaments(Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        if (team != null) {
            return team.getTournaments();
        }
        return null;
    }

    public void updateTeam(Team team) {
        teamRepository.save(team);
    }
}
