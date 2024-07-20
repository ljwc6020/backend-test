package kr.co.polycube.backendtest.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

}
