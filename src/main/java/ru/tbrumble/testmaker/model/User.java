package ru.tbrumble.testmaker.model;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class User {

    private String userLogin;

    private boolean isBlocked;

    private String userPassword;

    private String firstName;

    private String lastName;

    private String middleName;
}
