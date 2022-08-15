package Elunina_AE.car_wash.mapper;

import Elunina_AE.car_wash.dto.request.OperationRegReqDto;
import Elunina_AE.car_wash.dto.request.OperationReqDto;
import Elunina_AE.car_wash.dto.response.OperationResDto;
import Elunina_AE.car_wash.model.Operation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OperationMapper {

    Operation toEntity(OperationReqDto operationReqDto);

    OperationResDto toResponse(Operation operation);

    Operation toEntity(OperationRegReqDto dto);
}
