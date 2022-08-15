package Elunina_AE.car_wash.service;

import Elunina_AE.car_wash.model.Booking;
import Elunina_AE.car_wash.repo.BookingRepo;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@Data
public class BookingService {

    private final BookingRepo orderRepo;

    public Page<Booking> getBookings(Specification<Booking> specification, Pageable pageable) {
        return orderRepo.findAll(Specification.where(specification), pageable);
    }

}
