package ru.tbrumble.testmaker.dto;

import org.springframework.stereotype.Service;
import ru.tbrumble.testmaker.model.entity.SwitchEntity;
import ru.tbrumble.testmaker.repo.SwitchRepo;

@Service
public class SwitchDto {
    private final SwitchRepo switchRepo;

    public SwitchDto(SwitchRepo switchRepo) {
        this.switchRepo = switchRepo;
    }

    public boolean hasAccess(String switchName) {
        SwitchEntity switchEntity = switchRepo.getBySwitchName(switchName);
        return switchEntity.isValue();
    }
}
