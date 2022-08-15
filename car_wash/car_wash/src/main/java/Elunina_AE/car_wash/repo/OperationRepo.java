package Elunina_AE.car_wash.repo;

import Elunina_AE.car_wash.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepo extends JpaRepository<Operation,Long> {
}
