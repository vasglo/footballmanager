package football.Service;

import football.dto.PlayerDto;
import football.dto.TeamDto;

import java.util.List;
import java.util.Set;

public interface TeamService {


    TeamDto create(TeamDto dto);

    TeamDto get(Long id);

    List<TeamDto> getAll();

    TeamDto update(TeamDto dto);

    TeamDto delete(TeamDto dto);

    Set<PlayerDto> getTeamPlayers(Long courseId);

    TeamDto deletePlayerFromTeamById(TeamDto dto, Long playerId);

    TeamDto addPlayerToTeamById(Long teamId, PlayerDto player);
}
