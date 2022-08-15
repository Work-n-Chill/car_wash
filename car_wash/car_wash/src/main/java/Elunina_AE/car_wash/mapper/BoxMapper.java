package Elunina_AE.car_wash.mapper;

import Elunina_AE.car_wash.dto.request.BoxReqDto;
import Elunina_AE.car_wash.dto.response.BoxResDto;
import Elunina_AE.car_wash.model.Box;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BoxMapper {

    Box toEntity(BoxReqDto boxReqDto);

    BoxResDto toResponse(Box x);
}
