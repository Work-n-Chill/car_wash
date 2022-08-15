package Elunina_AE.car_wash.specification;

import Elunina_AE.car_wash.dto.request.BookingSearch;
import Elunina_AE.car_wash.model.Booking;
import Elunina_AE.car_wash.model.Box;
import Elunina_AE.car_wash.model.Status;
import Elunina_AE.car_wash.roles.Role;
import Elunina_AE.car_wash.roles.User;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class BookingSpecification implements Specification<Booking> {

    private BookingSearch bookingSearch;
    private Box box;
    private User user;

    @Override
    public Predicate toPredicate(Root<Booking> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (user.getRole().equals(Role.ROLE_OPERATOR))
            box=user.getOperator().getBox();
        if (Objects.nonNull(box)){
            Predicate equal = criteriaBuilder.equal(root.get(Booking_.BOX), box);
            predicates.add(equal);
        }
        if (Objects.nonNull(bookingSearch.getDate())){
            Predicate dateEqual=criteriaBuilder.equal(
                    root.get(Booking_.date),bookingSearch.getDate());
            predicates.add(dateEqual);
        }
        if (Objects.nonNull(bookingSearch.getStart())){
            Predicate startEqual=criteriaBuilder.greaterThanOrEqualTo(root.get(Booking_.startTime),
                    bookingSearch.getStart());
            predicates.add(startEqual);
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    public static Specification<Booking> revenuePredicate(LocalDate fromDate, LocalDate toDate) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate=criteriaBuilder.equal(root.get(Booking_.bookingStatus), Status.DONE);
            if (Objects.nonNull(fromDate))
                predicate= criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get(Booking_.date),fromDate));
            if (Objects.nonNull(toDate))
                predicate=criteriaBuilder.and(predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get(Booking_.date),toDate));
            return predicate;
        };
    }

    public static Specification<Booking> userActiveOrders(User user){
        return (root, query, criteriaBuilder) -> {
            Predicate equalUser = criteriaBuilder.equal(root.get(Booking_.user), user);
            Predicate isActive=criteriaBuilder.equal(root.get(Booking_.orderStatus), Status.CREATED);
            return criteriaBuilder.and(equalUser,isActive);
        };
    }
}
