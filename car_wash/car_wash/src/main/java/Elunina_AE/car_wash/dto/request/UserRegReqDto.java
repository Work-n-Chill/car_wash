package Elunina_AE.car_wash.dto.request;

import Elunina_AE.car_wash.annotation.UniqueEmail;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegReqDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String surname;

    @Size(min = 6)
    @NotBlank
    @UniqueEmail
    private String email;

    @Size(min = 8)
    @NotBlank
    private String password;
}
