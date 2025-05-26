package lt.vu.psk.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lt.vu.psk.dto.TournamentDTO;
import lt.vu.psk.entity.Team;
import lt.vu.psk.entity.Tournament;
import lt.vu.psk.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    public void createTournament(TournamentDTO tournamentDto) {
        var tournament = Tournament.builder()
                .name(tournamentDto.getName())
                .prize(tournamentDto.getPrize())
                .build();
        tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournamentById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId).orElseThrow(() -> new EntityNotFoundException("Tournament with ID '" + tournamentId + "' not found."));
    }

    public List<Team> getTournamentTeams(Long tournamentId) {
        Tournament tournament = getTournamentById(tournamentId);
        if (tournament != null) {
            return tournament.getTeams();
        }
        return null;
    }
}
