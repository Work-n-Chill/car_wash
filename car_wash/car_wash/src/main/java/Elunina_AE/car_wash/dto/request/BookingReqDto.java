package Elunina_AE.car_wash.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class BookingReqDto {

    @NotNull
    @JsonFormat(pattern = "HH-mm")
    private LocalTime start;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Size(min = 1)
    private Set<OperationReqDto> appointments;
}
