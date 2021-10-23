package football.Service;

import football.Exceptions.PlayerNotFoundException;
import football.Exceptions.TeamNotFoundException;
import football.Repo.Entity.Player;
import football.Repo.Entity.Team;
import football.Repo.PlayerRepository;
import football.Repo.TeamRepository;
import football.Repo.UserRepository;
import football.dto.PlayerDto;
import football.dto.TeamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static football.dto.TeamDto.map;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamServiceImpl(TeamRepository teamRepository, PlayerRepository playerRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public TeamDto create(TeamDto dto) {
        log.info("Creating new team {}", dto);
        return map(teamRepository.save(map(dto)));
    }

    @Override
    public TeamDto get(Long id) {
        log.info("Getting team by id {}", id);
        return teamRepository.findById(id).map(TeamDto::map).orElseThrow(TeamNotFoundException::new);
    }

    @Override
    public List<TeamDto> getAll() {
        log.info("Getting all teams");
        return teamRepository.findByOrderOrderByName().stream().map(TeamDto::map).collect(Collectors.toList());
    }

    @Override
    public TeamDto update(TeamDto dto) {
        return map(teamRepository.save(map(dto)));
    }

    @Override
    public TeamDto delete(TeamDto dto) {
        log.info("Delete team {}", dto);
        return null;
    }

    @Override
    public Set<PlayerDto> getTeamPlayers(Long id) {
        log.info("Get all players from team {}", id);
        return teamRepository.findById(id).map(value -> value
                .getPlayers().stream().map(PlayerDto::map).collect(Collectors.toSet())).orElseThrow(TeamNotFoundException::new);
    }

    @Override
    public TeamDto deletePlayerFromTeamById(TeamDto dto, Long playerId) {
        Player playerToBeRemoved = playerRepository.findById(playerId).orElseThrow(PlayerNotFoundException::new);
        Team team = teamRepository.findById(dto.getId()).orElse(null);
        if (team != null && playerToBeRemoved != null) {
            team.getPlayers().remove(playerToBeRemoved);
            log.info("Deleting player {} from team {}", playerToBeRemoved.getName() + " " + playerToBeRemoved.getSecondName(), dto);
            return TeamDto.map(teamRepository.save(team));
        }
        return null;
    }

    @Override
    public TeamDto addPlayerToTeamById(Long teamId, PlayerDto player) {
        Team team = teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new);
        Player playerToBeAdded = playerRepository.findById(player.getId()).orElse(null);
        if (team != null && playerToBeAdded != null) {
            team.getPlayers().add(playerToBeAdded);
            log.info("Adding player {} to team {}", player.getName() + " " + player.getSecondName(), team.getName());
            return TeamDto.map(teamRepository.save(team));
        }
        return null;
    }
}
