package Elunina_AE.car_wash.dto.response;

import Elunina_AE.car_wash.model.Status;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class BookingResDto {

    private Long id;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate date;

    private Status status;

    private Set<OperationResDto> operations;

    private UserResDto user;

    private BoxResDto box;
}
