package ru.tbrumble.testmaker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tbrumble.testmaker.model.entity.SwitchEntity;


@Repository
public interface SwitchRepo extends JpaRepository<SwitchEntity, Long> {
    SwitchEntity getBySwitchName(String switchName);
}
