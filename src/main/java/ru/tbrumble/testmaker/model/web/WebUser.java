package ru.tbrumble.testmaker.model.web;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@ApiModel("User object web model")
public class WebUser {
//    @ApiModelProperty(
//            value = "User login",
//            name = "userLogin",
//            dataType = "String",
//            example = "awesome_login")
    private String userLogin;

//    @ApiModelProperty(
//            value = "User blocked param",
//            name = "isBlocked",
//            dataType = "boolean",
//            example = "true/false")
    private boolean isBlocked;

//    @ApiModelProperty(
//            value = "User hash password hash",
//            name = "userPassword",
//            dataType = "String",
//            example = "3a41de8bb0df1239020609dc617e8421")
    private String userPassword;

//    @ApiModelProperty(
//            value = "User first name",
//            name = "firstName",
//            dataType = "String",
//            example = "Ivan")
    private String firstName;

//    @ApiModelProperty(
//            value = "User last name",
//            name = "lastName",
//            dataType = "String",
//            example = "Ivanov")
    private String lastName;

//    @ApiModelProperty(
//            value = "User middle name",
//            name = "middleName",
//            dataType = "String",
//            example = "Ivanovich")
    private String middleName;
}
