package ru.tbrumble.testmaker.services;

//import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.tbrumble.testmaker.dto.SwitchDto;
import ru.tbrumble.testmaker.dto.UserDto;
import ru.tbrumble.testmaker.exception.SwitchException;
//import ru.tbrumble.testmaker.exception.VerifyException;
import ru.tbrumble.testmaker.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserDto userDto;
    private final SwitchDto switchDto;

    public UserServiceImpl(UserDto userDto, SwitchDto switchDto) {
        this.userDto = userDto;
        this.switchDto = switchDto;
    }

    @Override
    public Boolean blockUserByLogin(String login) {
        return userDto.blockUserByLogin(login);
    }

    @Override
    public Boolean unblockUserByLogin(String login) {
        return userDto.unblockUserByLogin(login);
    }

    @Override
    public Boolean compareUserPassword(String login, String hashPassword) {
        return userDto.compareUserPassword(login, hashPassword);
    }

    @Override
    public List<User> getAllUsers() {
        ModelMapper modelMapper = new ModelMapper();
        if (switchDto.hasAccess("dbo_access_userverify")) {
            return Arrays.asList(modelMapper.map(userDto.getAllUsers(), User[].class));
        }
        else {
            throw new SwitchException("No Access");
        }
    }
}
