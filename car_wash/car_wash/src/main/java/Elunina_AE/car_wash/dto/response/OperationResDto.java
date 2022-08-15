package Elunina_AE.car_wash.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OperationResDto {


    private Long id;

    private String name;

    private BigDecimal cost;

    private Integer duration;
}
