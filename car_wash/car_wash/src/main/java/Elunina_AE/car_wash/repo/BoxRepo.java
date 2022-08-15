package Elunina_AE.car_wash.repo;

import Elunina_AE.car_wash.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BoxRepo extends JpaRepository<Box, Long>, JpaSpecificationExecutor<Box> {

    @Query(value = """
select distinct b.* from boxes bx join bookings bk  on bk.box_id=bx.id
where bk."date" =:date and bk.status  in ('CREATED','ARRIVED') and bk.user_id=:userId
and(
(bk.start <= make_time(:h, :m,0) and  make_time(:h, :m,0)<bk.end)
    )
)
""",
            nativeQuery = true)
    List<Box> getBusyBoxes(@Param("date") LocalDate date,
                           @Param("h") int hour,
                           @Param("m") int minute,
                           @Param("duration") int duration);

    @Query(value = """
select distinct b.* from boxes bx
join operators o on o.box_id=bx.id
where
((make_time(:h, :m,0) between bx."open" and bx."close") and
cast(make_time(:h, :m,0) +interval '1 minute'*cast(ceil(bx.perfRatio *:duration) as int) as time)
between bx."open" and bx."close")
""" ,
            nativeQuery = true)
    List<Box> getOpenBoxesWithOperator(@Param("h") int hour,
                                       @Param("m") int minute,
                                       @Param("duration") int duration);
}
