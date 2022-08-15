package Elunina_AE.car_wash.dto.response;

import lombok.Data;

import java.time.LocalTime;

@Data
public class BoxResDto {

    private Long id;

    private LocalTime open;

    private LocalTime close;
}
