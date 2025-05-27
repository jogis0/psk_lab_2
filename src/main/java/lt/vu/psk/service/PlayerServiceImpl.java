package lt.vu.psk.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lt.vu.psk.dto.PlayerDTO;
import lt.vu.psk.entity.Player;
import lt.vu.psk.repository.PlayerRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("basePlayerService")
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private static final int MAX_RETRIES = 3;
    private final PlayerRepository playerRepository;

    @Override
    public void createPlayer(PlayerDTO playerDTO) {
        var player = Player.builder()
                .firstName(playerDTO.getFirstName())
                .lastName(playerDTO.getLastName())
                .age(playerDTO.getAge())
                .height(playerDTO.getHeight())
                .weight(playerDTO.getWeight())
                .country(playerDTO.getCountry())
                .jerseyNumber(playerDTO.getJerseyNumber())
                .build();
        playerRepository.save(player);
    }

    @Override
    public Player getPlayer(Long id) {
        var player = playerRepository.findById(id);
        if (player.isEmpty())
            throw new RuntimeException("Player not found with ID: " + id);
        return player.get();
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public boolean updatePlayer(Player player) {
        try {
            doUpdate(player);
            return true;
        } catch (ObjectOptimisticLockingFailureException e) {
            return false;
        }
    }

    @Transactional
    public void doUpdate(Player player) {
        playerRepository.save(player);
    }
}
