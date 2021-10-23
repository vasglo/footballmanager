package football.dto;

import football.Repo.Entity.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class PlayerDto {
    private Long id;
    @NotBlank(message = "Enter player's name")
    private String name;
    @NotBlank(message = "Enter player's second name ")
    private String secondName;
    @NotBlank(message = "Enter player's experience")
    private Integer experience;
    @NotBlank(message = "Enter player's age")
    private Integer age;

    public static PlayerDto map(Player player){
        return PlayerDto.builder()
                .id(player.getId())
                .name(player.getName())
                .secondName(player.getSecondName())
                .experience(player.getExperience())
                .age(player.getAge())
                .build();
    }

    public static Player map(PlayerDto player){
        return Player.builder()
                .id(player.getId())
                .name(player.getName())
                .secondName(player.getSecondName())
                .experience(player.getExperience())
                .age(player.getAge())
                .build();
    }

}
