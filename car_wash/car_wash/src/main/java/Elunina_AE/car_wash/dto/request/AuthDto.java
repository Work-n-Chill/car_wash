package Elunina_AE.car_wash.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthDto {

    @Size(min = 8, max = 64)
    @NotBlank
    private String email;

    @Size(min = 8, max = 64)
    @NotBlank
    private String password;
}
