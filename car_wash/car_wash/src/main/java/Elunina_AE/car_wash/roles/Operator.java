package Elunina_AE.car_wash.roles;

import Elunina_AE.car_wash.model.Box;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@Table(name = "operators")
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany (mappedBy = "operator", fetch = FetchType.LAZY)
    @JoinColumn(name = "box_id",referencedColumnName = "id")
    @NotNull
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Box> boxes;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @NotNull
    private User user;

    @Column(name = "discount_min")
    private Integer discountMin;

    @Column(name = "discount_max")
    private Integer discountMax;

}
