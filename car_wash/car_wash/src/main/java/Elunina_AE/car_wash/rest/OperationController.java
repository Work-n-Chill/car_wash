package Elunina_AE.car_wash.rest;

import Elunina_AE.car_wash.dto.request.OperationRegReqDto;
import Elunina_AE.car_wash.dto.response.OperationResDto;
import Elunina_AE.car_wash.mapper.OperationMapper;
import Elunina_AE.car_wash.model.Operation;
import Elunina_AE.car_wash.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;
    private final OperationMapper operationMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Long createOperation(@Validated @RequestBody OperationRegReqDto dto) {
        Operation operation = operationMapper.toEntity(dto);
        return operationService.createService(operation);
    }

    @GetMapping
    public Page<OperationResDto> getOperations(Integer pageNumber, Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return operationService.getOperations(pageRequest).map(op -> operationMapper.toResponse(op));
    }
}
