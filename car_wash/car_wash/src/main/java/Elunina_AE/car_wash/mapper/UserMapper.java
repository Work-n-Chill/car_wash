package Elunina_AE.car_wash.mapper;

import Elunina_AE.car_wash.dto.request.UserRegReqDto;
import Elunina_AE.car_wash.dto.response.UserResDto;
import Elunina_AE.car_wash.roles.User;
import Elunina_AE.car_wash.security.UserAppDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserAppDto toAppDto(User user);

    UserResDto toResponse(User user);

    User toEntity(UserRegReqDto dto);
}
