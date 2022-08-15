package Elunina_AE.car_wash.model;

import Elunina_AE.car_wash.roles.Operator;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "boxes")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double perfRatio;

    private LocalTime open;

    private LocalTime close;

    @ManyToOne
    @NotNull
    private Operator operator;
}
