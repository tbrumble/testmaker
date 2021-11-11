package ru.tbrumble.testmaker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tbrumble.testmaker.model.entity.UserEntity;


@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity getByUserLogin(String userLogin);
}
