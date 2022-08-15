package Elunina_AE.car_wash.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class DiscountReqDto {

    @Min(value = 0)
    @Max(value = 5)
    private Integer min;

    @Min(value = 10)
    @Max(value = 25)
    private Integer max;
}
