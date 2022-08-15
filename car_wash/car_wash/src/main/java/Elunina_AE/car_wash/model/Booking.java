package Elunina_AE.car_wash.model;

import Elunina_AE.car_wash.roles.User;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Data
@Table(name = "bookings")
public class Booking {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        private LocalDate date;

        @NotNull
        private LocalTime start;

        @NotNull
        private LocalTime end;

        @NotNull
        @Column(name="cost")
        private BigDecimal cost;

        @ManyToMany(fetch = FetchType.LAZY, cascade =
                {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.REFRESH})
        @JoinTable(
                name = "booking_operation",
                joinColumns = @JoinColumn(name="booking_id"),
                inverseJoinColumns = @JoinColumn(name ="operation_id")
        )
        @Fetch(value=FetchMode.SUBSELECT)
        private Set<Operation> operations;

        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        @NotNull
        private User user;

        @ManyToOne
        @JoinColumn(name = "box_id", referencedColumnName = "id")
        @NotNull
        private Box box;

        @Column(columnDefinition = "boolean default false")
        private Boolean isConfirmed=false;

        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "varchar(255) default 'CREATED'")
        @NotNull
        private Status status;
}
