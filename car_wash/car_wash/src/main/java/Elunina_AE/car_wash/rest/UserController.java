package Elunina_AE.car_wash.rest;

import Elunina_AE.car_wash.dto.request.OperatorReqDto;
import Elunina_AE.car_wash.dto.response.BookingResDto;
import Elunina_AE.car_wash.mapper.BookingMapper;
import Elunina_AE.car_wash.model.Booking;
import Elunina_AE.car_wash.model.Box;
import Elunina_AE.car_wash.roles.User;
import Elunina_AE.car_wash.service.BookingService;
import Elunina_AE.car_wash.service.BoxService;
import Elunina_AE.car_wash.service.OperatorService;
import Elunina_AE.car_wash.service.UserService;
import Elunina_AE.car_wash.specification.BookingSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.InvalidRoleValueException;
import java.util.Set;

@Controller
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OperatorService operatorService;
    private final BoxService boxService;
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @GetMapping("/{id}/bookings")
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public Page<BookingResDto> getBookings(@PathVariable Long id, Integer pageNumber,
                                           Integer pageSize) {
        User user = userService.getUser(id);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Specification<Booking> orderSpecification = BookingSpecification.userActiveOrders(user);
        return bookingService.getBookings(orderSpecification, pageRequest).map(o -> bookingMapper.toResponse(o));
    }

    @PostMapping("/{id}/make-operator")
    @PreAuthorize("hasRole('ADMIN')")
    public Long makeOperator(@Validated @RequestBody OperatorReqDto dto, @PathVariable Long id)
            throws InvalidRoleValueException {
        User user = userService.getUser(id);
        Box box = boxService.getBox(dto.getBoxId());
        return operatorService.createOperator(user, (Set<Box>) box);
    }

}
