package Elunina_AE.car_wash.rest;

import Elunina_AE.car_wash.dto.request.DiscountReqDto;
import Elunina_AE.car_wash.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/operators")
public class OperatorController {

    private final OperatorService operatorService;

    @PatchMapping("/{id}/set-discount")
    @PreAuthorize("hasRole('ADMIN')")
    public void setDiscount(@Validated @RequestBody DiscountReqDto dto, @PathVariable Long id){
        operatorService.setDiscount(id, dto.getMin(), dto.getMax());
    }
}
