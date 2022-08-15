package Elunina_AE.car_wash.roles;

import Elunina_AE.car_wash.model.Booking;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "users", uniqueConstraints= @UniqueConstraint(columnNames={"user_name"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String login;

    @NotNull
    private String firstName;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @OneToOne(mappedBy = "user")
    private Operator operator;

    private Integer discount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Fetch(value=FetchMode.SUBSELECT)
    private Set<Booking> bookings;

    @PostConstruct
    private void setDefaultRole(){
        this.setRole(Role.ROLE_USER);
    }
}
