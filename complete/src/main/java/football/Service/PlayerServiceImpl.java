package football.Service;

import football.Exceptions.PlayerNotFoundException;
import football.Exceptions.TeamNotFoundException;
import football.Repo.Entity.Player;
import football.Repo.Entity.Team;
import football.Repo.PlayerRepository;
import football.Repo.TeamRepository;
import football.dto.PlayerDto;
import football.dto.TeamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

import static football.dto.PlayerDto.map;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public PlayerDto create(PlayerDto dto) {
        log.info("Creating new player {}", dto);
        return map(playerRepository.save(map(dto)));
    }

    @Override
    public PlayerDto update(PlayerDto playerDto) {
        log.info("Updating player {}", playerDto);
        return PlayerDto.map(playerRepository.save(map(playerDto)));
    }

    @Transactional
    @Override
    public PlayerDto moveToOtherTeam(TeamDto oldTeam, Long newTeamId, Long playerId) {
        //Have to be edited
        Team oldPlayersTeam = teamRepository.findById(oldTeam.getId()).orElse(null);
        Team newTeam = teamRepository.findById(newTeamId).orElseThrow(TeamNotFoundException::new);
        Player player = playerRepository.findById(playerId).orElseThrow(PlayerNotFoundException::new);
        assert oldPlayersTeam != null;
        Integer fullPrice = (player.getPrice() * oldPlayersTeam.getCommission()) / 100;
        if (newTeam != null && oldPlayersTeam != newTeam && newTeam.getMoney() >= fullPrice) {
            newTeam.setMoney(newTeam.getMoney() - fullPrice);
            player.setTeam(newTeam);
            oldPlayersTeam.setMoney(oldPlayersTeam.getMoney() + fullPrice);
            log.info("Team {} bye player {} from {} ", newTeam.getName(), player.getName() + " " + player.getSecondName(), oldPlayersTeam.getName());
            return PlayerDto.map(player);
        } else {
            assert newTeam != null;
            if (newTeam.getMoney() < fullPrice) {
                log.info("Team {} dont have enough money to bye {}", newTeam.getName(), player.getName() + " " + player.getSecondName());
            }
        }
        return null;
    }

    @Override
    public void deletePlayerById(Long playerId) {
        playerRepository.deleteById(playerRepository.findById(playerId).orElseThrow(PlayerNotFoundException::new).getId());
        log.info("deleting player {}", playerId);
    }

    @Override
    public Set<PlayerDto> getAllPlayers() {
        return playerRepository.findAll().stream().map(PlayerDto::map).collect(Collectors.toSet());
    }
}
