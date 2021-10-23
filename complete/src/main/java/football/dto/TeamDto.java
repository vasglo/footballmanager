package football.dto;

import football.Repo.Entity.Team;
import football.Repo.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class TeamDto {
    private Long id;
    @NotBlank(message = "Enter team name")
    private String name;
    @NotBlank(message = "Enter commission")
    private Integer commission;
    @NotBlank(message = "Enter team money")
    private Long money;


    public static TeamDto map(Team team){
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .money(team.getMoney())
                .commission(team.getCommission())
                .build();
    }

    public static Team map(TeamDto teamDto){
        return Team.builder()
                .id(teamDto.getId())
                .name(teamDto.getName())
                .money(teamDto.getMoney())
                .commission(teamDto.getCommission())
                .build();
    }
}
