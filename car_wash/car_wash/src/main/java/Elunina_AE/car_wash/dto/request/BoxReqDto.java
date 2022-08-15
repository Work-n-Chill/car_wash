package Elunina_AE.car_wash.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
public class BoxReqDto {

        @NotNull
        private Double perfRatio;

        @NotNull
        @JsonFormat(pattern = "HH-mm")
        private LocalTime open;

        @NotNull
        @JsonFormat(pattern = "HH-mm")
        private LocalTime close;

}
