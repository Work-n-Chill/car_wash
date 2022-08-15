package Elunina_AE.car_wash.service;

import Elunina_AE.car_wash.model.Box;
import Elunina_AE.car_wash.repo.BoxRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoxService {
    private final BoxRepo boxRepo;

    @Transactional
    public Long createBox(Box box) {
        return boxRepo.save(box).getId();
    }

    public Box getBox(Long boxId) {
        if (Objects.isNull(boxId))
            return null;
        return boxRepo.findById(boxId).orElseThrow(() ->
                new EntityNotFoundException("не найден бокс " + boxId));
    }

    @Transactional
    public void updateBox(Long id, Box updateBox) {
        Box box = getBox(id);
        box.setPerfRatio(updateBox.getPerfRatio());
        box.setOpen(updateBox.getOpen());
        box.setClose(updateBox.getClose());
        createBox(box);
    }
}
