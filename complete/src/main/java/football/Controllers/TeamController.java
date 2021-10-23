package football.Controllers;

import football.Service.TeamService;
import football.dto.PlayerDto;
import football.dto.TeamDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }

    @PostMapping
    public TeamDto create(@RequestBody TeamDto dto){
        return teamService.create(dto);
    }

    @GetMapping("/all")
    public List<TeamDto> getAll(){
        return teamService.getAll();
    }

    @GetMapping("/{teamId}")
    public TeamDto getTeamById(@PathVariable Long teamId){
        return teamService.get(teamId);
    }

    @PostMapping
    public TeamDto update(@RequestBody TeamDto dto){
        return teamService.update(dto);
    }

    @DeleteMapping
    public TeamDto delete(@RequestBody TeamDto dto){
        return teamService.delete(dto);
    }

    @GetMapping({"/teamId/players"})
    public Set<PlayerDto> getTeamPlayers(@PathVariable Long teamId){
        return teamService.getTeamPlayers(teamId);
    }

    @DeleteMapping
    public TeamDto deletePlayerFromTeamById (@RequestBody TeamDto dto, @RequestParam Long playerId){
        return teamService.deletePlayerFromTeamById(dto,playerId);
    }

    @PostMapping
    public TeamDto addPlayerToTeamById(@RequestParam Long teamId, @RequestBody PlayerDto dto){
    return teamService.addPlayerToTeamById(teamId,dto);
    }

}
