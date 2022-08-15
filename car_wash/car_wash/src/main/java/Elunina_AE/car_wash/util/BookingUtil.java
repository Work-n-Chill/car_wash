package Elunina_AE.car_wash.util;

import Elunina_AE.car_wash.model.Booking;
import Elunina_AE.car_wash.model.Box;
import Elunina_AE.car_wash.model.Operation;
import Elunina_AE.car_wash.repo.BookingRepo;
import Elunina_AE.car_wash.repo.BoxRepo;
import Elunina_AE.car_wash.roles.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookingUtil {

    private final BoxRepo boxRepo;
    private final BookingRepo bookingRepo;
    @Value("${time_period}")
    private Long timePeriod;
    @Value("${only_weekday}")
    private boolean onlyWeekday;
    @Value("${access_day_interval}")
    private Integer dayInterval;
    @Value("${access_arrived_time}")
    private Integer accessArrivedTime;

    public BookingUtil setFreeBox(Booking booking, Set<Operation> operations, User user) {
        int duration = operations.stream().mapToInt(Operation::getDuration).sum();
        LocalTime startTime = booking.getStart();
        List<Box> openBoxes=boxRepo.getOpenBoxesWithOperator(startTime.getHour(),
                startTime.getMinute(), duration);
        if (openBoxes.isEmpty())
            throw new EntityNotFoundException("свободных боксов не найдено");
        List<Box> busyBoxes = boxRepo.getBusyBoxes(booking.getDate(),
                startTime.getHour(), startTime.getMinute(), duration);
        if (openBoxes.isEmpty())
            throw new EntityNotFoundException("свободных боксов не найдено");
        if (!busyBoxes.isEmpty())
            openBoxes.removeAll(busyBoxes);
        checkAvailableBoxes(openBoxes, booking,duration);
        return this;
    }

    private void checkAvailableBoxes(List<Box> openBoxes, Booking booking, int duration){

    }

}
