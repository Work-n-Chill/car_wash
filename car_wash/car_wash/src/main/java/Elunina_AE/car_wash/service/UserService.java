package Elunina_AE.car_wash.service;

import Elunina_AE.car_wash.repo.UserRepo;
import Elunina_AE.car_wash.roles.Role;
import Elunina_AE.car_wash.roles.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @PostAuthorize("hasRole('ADMIN') || " +
            "returnObject.email.equals(authentication.name)")
    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("нет пользователя" + id));
    }

    @PostAuthorize("hasAnyRole('ADMIN','OPERATOR') || " +
            "returnObject.email.equals(authentication.name)")
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("нет почты", email)));
    }

    @Transactional
    public Long createUser(User user) {
        user.setPassword(passwordEncoder.encode(
                user.getPassword()
        ));
        user.setRole(Role.ROLE_USER);
        return userRepo.save(user).getId();
    }

}
