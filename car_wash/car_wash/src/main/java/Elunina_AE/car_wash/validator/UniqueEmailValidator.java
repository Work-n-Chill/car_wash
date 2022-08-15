package Elunina_AE.car_wash.validator;

import Elunina_AE.car_wash.annotation.UniqueEmail;
import Elunina_AE.car_wash.repo.UserRepo;
import Elunina_AE.car_wash.roles.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator {

    private final UserRepo userRepo;

    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    public boolean isValid(@Valid String email, ConstraintValidatorContext context) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent())
            return false;
        else
            return true;
    }
}
