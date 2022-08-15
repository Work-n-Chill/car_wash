package Elunina_AE.car_wash.rest;

import Elunina_AE.car_wash.dto.request.BoxReqDto;
import Elunina_AE.car_wash.mapper.BoxMapper;
import Elunina_AE.car_wash.model.Box;
import Elunina_AE.car_wash.service.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/boxes")
@RequiredArgsConstructor
public class BoxController {

    private final BoxService boxService;
    private final BoxMapper boxMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createBox(@Validated @RequestBody BoxReqDto dto) {
        Box box = boxMapper.toEntity(dto);
        return boxService.createBox(box);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateBox(@PathVariable Long id, @Validated @RequestBody BoxReqDto dto) {
        Box updatedBox = boxMapper.toEntity(dto);
        boxService.updateBox(id, updatedBox);
    }
}
