package ru.tbrumble.testmaker.controller;

//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.tbrumble.testmaker.model.web.WebUser;
import ru.tbrumble.testmaker.services.UserService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all")
 //   @ApiOperation("Получение списка пользователей")
    public List<WebUser> getAllUser() {
        ModelMapper modelMapper = new ModelMapper();
        return Arrays.asList(modelMapper.map(userService.getAllUsers(), WebUser[].class));
    }

    @PostMapping("/block-by-name/login/{login}")
 //   @ApiOperation("Блокирование пользователя по логину")
    public Boolean blockUserByLogin(
          //  @ApiParam(value = "Логин пользователя", required = true)
            @PathVariable("login") String login) {
        return userService.blockUserByLogin(login);
    }

    @PostMapping("/unblock-by-name/login/{login}")
   // @ApiOperation("Разблокирование пользователя по логину")
    public Boolean unblockUserByLogin(
       //     @ApiParam(value = "Логин пользователя", required = true)
            @PathVariable("login") String login) {
        return userService.unblockUserByLogin(login);
    }

  //  @ApiOperation("Проверка пароля пользователя")
    @PostMapping("/compare-user-password/login/{login}/hashPassword/{hashPassword}")
    public Boolean compareUserPassword(
  //          @ApiParam(value = "Логин пользователя", required = true)
            @PathVariable("login") String login,
  //          @ApiParam(value = "Пароль пользователя", required = true)
            @PathVariable("hashPassword") String hashPassword) {
        return userService.compareUserPassword(login, hashPassword);
    }
}
