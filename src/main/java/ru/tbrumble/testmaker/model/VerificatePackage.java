package ru.tbrumble.testmaker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.tbrumble.testmaker.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class VerificatePackage {
    private UUID operationUUID;
    private LocalDate localDate;
    private User user;
    private boolean verificateResult;
}
