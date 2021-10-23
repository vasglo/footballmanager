package football.Controllers;

import football.Service.PlayerService;
import football.dto.PlayerDto;
import football.dto.TeamDto;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public PlayerDto createNewPlayer(@RequestBody PlayerDto dto) {
        return playerService.create(dto);
    }

    @PostMapping
    public PlayerDto updatePlayer(@RequestBody PlayerDto dto) {
        return playerService.update(dto);
    }

    @PostMapping
    public PlayerDto movePlayerToOtherTeam(@RequestBody TeamDto dto, @RequestParam Long newTeamId, @RequestParam Long playerId) {
        return playerService.moveToOtherTeam(dto, newTeamId, playerId);
    }

    @DeleteMapping
    public void deletePlayerById(@RequestParam Long playerId) {
        playerService.deletePlayerById(playerId);
    }

    @GetMapping("/all")
    public Set<PlayerDto> getAllPlayers() {
        return playerService.getAllPlayers();
    }
}
