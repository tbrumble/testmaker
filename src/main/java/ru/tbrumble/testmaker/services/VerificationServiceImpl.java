package ru.tbrumble.testmaker.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.tbrumble.testmaker.dto.UserDto;
import ru.tbrumble.testmaker.exception.VerifyException;
import ru.tbrumble.testmaker.model.User;
import ru.tbrumble.testmaker.model.VerificatePackage;
import ru.tbrumble.testmaker.model.web.WebVerificatePackage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class VerificationServiceImpl implements VerificationService{
    private final UserDto userDto;

    public VerificationServiceImpl(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public WebVerificatePackage verifyUserData(WebVerificatePackage webVerificatePackage) {
        ModelMapper modelMapper = new ModelMapper();
        VerificatePackage verificatePackage = modelMapper.map(webVerificatePackage, VerificatePackage.class);

        if (verificatePackage != null) {
            List<User> users = Arrays.asList(modelMapper.map(userDto.getAllUsers(), User[].class));

            User selectedUser = users.stream()
                    .filter(user -> user.getUserLogin().equals(verificatePackage.getUser().getUserLogin()))
                    .findAny()
                    .orElseThrow(() -> new VerifyException("Bad credentials"));

            webVerificatePackage.setLocalDate(LocalDate.now());
            webVerificatePackage.setOperationUUID(UUID.randomUUID());

            if (checkUserData(webVerificatePackage, selectedUser)) {
                webVerificatePackage.setVerificateResult(Boolean.TRUE);
            } else {
                webVerificatePackage.setVerificateResult(Boolean.FALSE);
            }
        } else
        {
            throw new VerifyException("Bad credentials");
        }
        return webVerificatePackage;
    }

    @Override
    public WebVerificatePackage getExample() {
        ModelMapper modelMapper = new ModelMapper();
        List<User> users = Arrays.asList(modelMapper.map(userDto.getAllUsers(), User[].class));

        User selectedUser = users.stream()
                .findFirst()
                .orElseThrow(() -> new VerifyException("Bad credentials"));

        VerificatePackage verificatePackage = new VerificatePackage();
        verificatePackage.setUser(selectedUser);
        verificatePackage.setOperationUUID(UUID.randomUUID());
        verificatePackage.setLocalDate(LocalDate.now());
        verificatePackage.setVerificateResult(Boolean.TRUE);

        return modelMapper.map(verificatePackage, WebVerificatePackage.class);
    }

    private boolean checkUserData(WebVerificatePackage webVerificatePackage, User selectedUser) {
        return (!selectedUser.isBlocked()
                && (selectedUser.getUserPassword().equals(webVerificatePackage.getUser().getUserPassword()))
                && selectedUser.getFirstName().equals(webVerificatePackage.getUser().getFirstName())
                && selectedUser.getLastName().equals(webVerificatePackage.getUser().getLastName())
                && selectedUser.getMiddleName().equals(webVerificatePackage.getUser().getMiddleName())
        );
    }
}
