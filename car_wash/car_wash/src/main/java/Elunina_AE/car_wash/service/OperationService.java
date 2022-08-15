package Elunina_AE.car_wash.service;

import Elunina_AE.car_wash.model.Operation;
import Elunina_AE.car_wash.repo.OperationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperationService {

    private final OperationRepo operationRepo;

    @Transactional
    public Long createService(Operation service) {
        return operationRepo.save(service).getId();
    }

    public Set<Operation> getOperations(Set<Long> ids) {
        Set<Operation> set = new HashSet<>(operationRepo.findAllById(ids));
        if (set.isEmpty())
            throw new EntityNotFoundException("нет таких кодов");
        return set;
    }

    public Page<Operation> getOperations(Pageable pageable) {
        Page<Operation> page = operationRepo.findAll(pageable);
        if (page.getContent().isEmpty())
            throw new EntityNotFoundException("нет таких кодов");
        return page;
    }
}
