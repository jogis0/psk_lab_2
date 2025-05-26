package lt.vu.psk.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lt.vu.psk.dto.PlayerDTO;
import lt.vu.psk.entity.Player;
import lt.vu.psk.repository.PlayerRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private static final int MAX_RETRIES = 3;
    private final PlayerRepository playerRepository;

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

    public Player getPlayer(Long id) {
        var player = playerRepository.findById(id);
        if (player.isEmpty())
            throw new RuntimeException("Player not found with ID: " + id);
        return player.get();
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public void updatePlayer(Player player) {
        int attempt = 0;

        while (attempt < MAX_RETRIES) {
            try {
                doUpdate(player);
                return;
            } catch (ObjectOptimisticLockingFailureException e) {
                attempt++;
                System.out.println("Optimistic lock conflict. Retrying... (attempt " + attempt + ")");
                if (attempt >= MAX_RETRIES) {
                    throw new RuntimeException("Failed to update player after " + MAX_RETRIES + " attempts due to concurrent updates.", e);
                }
            }
        }
    }

    @Transactional
    public void doUpdate(Player player) {
        playerRepository.save(player);
    }
}
