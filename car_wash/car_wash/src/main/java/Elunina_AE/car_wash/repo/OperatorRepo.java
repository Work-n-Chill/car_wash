package Elunina_AE.car_wash.repo;

import Elunina_AE.car_wash.roles.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepo extends JpaRepository<Operator,Long> {
}
