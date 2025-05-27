package lt.vu.psk.service;

import lt.vu.psk.dto.PlayerDTO;
import lt.vu.psk.entity.Player;

import java.util.List;

public interface PlayerService {
    void createPlayer(PlayerDTO playerDTO);
    Player getPlayer(Long id);
    List<Player> getAllPlayers();
    boolean updatePlayer(Player player);
}
