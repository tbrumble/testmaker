package ru.tbrumble.testmaker.model.web;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import ru.tbrumble.testmaker.model.User;
//import ru.tbrumble.testmaker.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
//@ApiModel("Verificate web package")
public class WebVerificatePackage {
//    @ApiModelProperty(
//            value = "Operation uuid",
//            name = "operationUUID",
//            dataType = "UUID")
    private UUID operationUUID;

//    @ApiModelProperty(
//            value = "Verificate process started time",
//            name = "localDate",
//            dataType = "LocalDate")
    private LocalDate localDate;

//    @ApiModelProperty(
//            value = "User need to verificate",
//            name = "user",
//            dataType = "User")
    private User user;

//    @ApiModelProperty(
//            value = "Result verification",
//            name = "verificateResult",
//            dataType = "boolean")
    private boolean verificateResult;
}
