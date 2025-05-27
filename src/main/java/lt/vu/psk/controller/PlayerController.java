package lt.vu.psk.controller;

import lombok.RequiredArgsConstructor;
import lt.vu.psk.dto.PlayerDTO;
import lt.vu.psk.entity.Player;
import lt.vu.psk.service.CalculationService;
import lt.vu.psk.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final CalculationService calculationService;

    @PostMapping
    public void createPlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.createPlayer(playerDTO);
    }

    @GetMapping
    public List<Player> getPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{userId}")
    public Player getPlayer(@PathVariable Long userId) {
        return playerService.getPlayer(userId);
    }

    @PutMapping
    public ResponseEntity<?> updatePlayer(@RequestBody Player player) {
        return (playerService.updatePlayer(player)) ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PostMapping("/calculate/{userId}")
    public String calculateProbability(@PathVariable Long userId) {
        calculationService.calculateWhetherUserWillMakeTheNBA(userId);
        return "Started calculations for user ID " + userId;
    }

    @GetMapping("/result/{userId}")
    public String getTaskResult(@PathVariable Long userId) {
        return calculationService.getCalculationResult(userId);
    }
}
