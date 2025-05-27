package lt.vu.psk.service;

import lt.vu.psk.dto.PlayerDTO;
import lt.vu.psk.entity.Player;
import lt.vu.psk.interceptor.LoggedAction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@LoggedAction
@Primary
@ConditionalOnProperty(name = "features.decorator.enabled", havingValue = "true")
public class ExtendedPlayerService implements PlayerService {

    private final PlayerService delegate;

    public ExtendedPlayerService(@Qualifier("basePlayerService") PlayerService delegate) {
        this.delegate = delegate;
    }

    @Override
    public void createPlayer(PlayerDTO playerDTO) {
        System.out.println("Creating player...");
        delegate.createPlayer(playerDTO);
    }

    @Override
    public Player getPlayer(Long id) {
        System.out.println("Getting player...");
        return delegate.getPlayer(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        System.out.println("Getting all players...");
        return delegate.getAllPlayers();
    }

    @Override
    public boolean updatePlayer(Player player) {
        System.out.println("Updating player...");
        return delegate.updatePlayer(player);
    }
}
