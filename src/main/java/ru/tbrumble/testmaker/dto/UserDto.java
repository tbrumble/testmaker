package ru.tbrumble.testmaker.dto;

import org.springframework.stereotype.Service;
import ru.tbrumble.testmaker.model.entity.UserEntity;
import ru.tbrumble.testmaker.repo.UserRepo;

import java.util.List;

@Service
public class UserDto {
    private final UserRepo userRepo;

    public UserDto(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Boolean blockUserByLogin(String login) {
        UserEntity user = userRepo.getByUserLogin(login);
        if (user != null) {
            user.setBlocked(Boolean.TRUE);
            userRepo.save(user);
        }
        return userRepo.getByUserLogin(login).isBlocked();
    }

    public Boolean unblockUserByLogin(String login) {
        UserEntity user = userRepo.getByUserLogin(login);
        if (user != null) {
            user.setBlocked(Boolean.FALSE);
            userRepo.save(user);
        }
        return userRepo.getByUserLogin(login).isBlocked();
    }
    public boolean compareUserPassword(String login, String hashPassword) {
        UserEntity user = userRepo.getByUserLogin(login);
        if (user != null) {
            return user.getUserPassword().equals(hashPassword);
        } else
            return false;
    }

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }
}
