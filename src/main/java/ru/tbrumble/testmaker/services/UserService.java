package ru.tbrumble.testmaker.services;

import ru.tbrumble.testmaker.model.User;
import ru.tbrumble.testmaker.model.entity.UserEntity;

import java.util.List;

public interface UserService {
    Boolean blockUserByLogin(String login);
    Boolean unblockUserByLogin(String login);
    Boolean compareUserPassword(String login, String hashPassword);
    List<User> getAllUsers();
}
