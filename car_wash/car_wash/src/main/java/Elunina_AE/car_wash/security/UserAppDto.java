package Elunina_AE.car_wash.security;

import Elunina_AE.car_wash.roles.Role;
import lombok.Data;

@Data
public class UserAppDto {

    private Long id;

    private String firstName;

    private String surname;

    private String email;

    private String password;

    private Role role;

}
