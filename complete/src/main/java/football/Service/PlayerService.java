package football.Service;

import football.dto.PlayerDto;
import football.dto.TeamDto;

import java.util.Set;

public interface PlayerService {

    PlayerDto create(PlayerDto dto);

    PlayerDto update(PlayerDto playerDto);

    PlayerDto moveToOtherTeam(TeamDto oldTeam, Long newTeamId, Long playerId);

    void deletePlayerById(Long playerId);

    Set<PlayerDto> getAllPlayers();

}
