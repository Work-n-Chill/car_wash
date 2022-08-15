package Elunina_AE.car_wash.repo;

import Elunina_AE.car_wash.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Long>, JpaSpecificationExecutor<Booking> {

    @Query(value = """
select distinct b.* from bookings bk join boxes bx  on bk.box_id=bx.id
where bk."date" =:date and bk.status  in ('CREATED','ARRIVED') and bk.user_id=:userId
and((bk.start <= make_time(:h, :m,0) and  make_time(:h, :m,0)<bk.end)
    )
)
""",
            nativeQuery = true)
    List<Booking> getBooking(@Param("date") LocalDate date,
                                 @Param("h") int hour,
                                 @Param("m") int minute,
                                 @Param("duration") int duration,
                                 @Param("userId") Long userId);
}
