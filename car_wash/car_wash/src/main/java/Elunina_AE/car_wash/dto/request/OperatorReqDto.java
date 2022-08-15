package Elunina_AE.car_wash.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OperatorReqDto {

    @NotNull
    private Long boxId;
}
