package Elunina_AE.car_wash.mapper;

import Elunina_AE.car_wash.dto.request.BookingReqDto;
import Elunina_AE.car_wash.dto.response.BookingResDto;
import Elunina_AE.car_wash.model.Booking;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {OperationMapper.class, BoxMapper.class, UserMapper.class})
public interface BookingMapper {

    Booking toEntity(BookingReqDto orderReqDto);

    BookingResDto toResponse(Booking booking);
}
