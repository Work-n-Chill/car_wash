package Elunina_AE.car_wash.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OperationRegReqDto {

    @NotBlank
    private String name;

    @NotNull
    @Min(value = 30)
    private Integer duration;

    @NotNull
    @Min(value = 400)
    private BigDecimal cost;
}
