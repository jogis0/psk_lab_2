package lt.vu.psk.controller;

import lombok.RequiredArgsConstructor;
import lt.vu.psk.dto.PlayerDTO;
import lt.vu.psk.entity.Player;
import lt.vu.psk.service.PlayerService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    public void createPlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.createPlayer(playerDTO);
    }

    @GetMapping("/{userId}")
    public Player getPlayer(@PathVariable Long userId) {
        return playerService.getPlayer(userId);
    }

    @PutMapping//("/{userId}/{version}/{jerseyNumber}")
    public void updatePlayer(@RequestBody Player player /*@PathVariable Long userId, @PathVariable Integer jerseyNumber, @PathVariable Integer version*/) {
        playerService.updatePlayer(player);
    }
}
