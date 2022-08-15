package Elunina_AE.car_wash.service;

import Elunina_AE.car_wash.model.Box;
import Elunina_AE.car_wash.repo.OperatorRepo;
import Elunina_AE.car_wash.roles.Operator;
import Elunina_AE.car_wash.roles.Role;
import Elunina_AE.car_wash.roles.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.InvalidRoleValueException;
import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperatorService {
    private final OperatorRepo operatorRepo;

    private static String invalidMessage="неверная роль";

    @Transactional
    public void setDiscount(Long id, int min, int max) {
        Operator operator=getOperator(id);
        operator.setDiscountMax(max);
        operator.setDiscountMin(min);
        operatorRepo.save(operator);
    }

    public Operator getOperator(Long id) {
        return operatorRepo.findById(id).orElseThrow(()->
                new EntityNotFoundException("нет оператора "+id));
    }

    @Transactional
    public Long createOperator(User user, Set<Box> boxes) throws InvalidRoleValueException {
        if (!user.getRole().equals(Role.ROLE_USER))
            throw new InvalidRoleValueException(String.format(invalidMessage,user.getId(),
                    user.getRole().toString()));
        user.setRole(Role.ROLE_OPERATOR);
        Operator operator=new Operator();
        operator.setUser(user);
        operator.setBoxes(boxes);
        return operatorRepo.save(operator).getId();
    }

}
