package ru.tbrumble.testmaker;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tbrumble.testmaker.model.User;
import ru.tbrumble.testmaker.model.VerificatePackage;
import ru.tbrumble.testmaker.model.entity.UserEntity;
import ru.tbrumble.testmaker.model.web.WebUser;
import ru.tbrumble.testmaker.model.web.WebVerificatePackage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class ModelMapperTest {
    @Test
    public void mapVerifierPackage() {


        //fill values
        User user = new User()
                .setUserLogin(TestConsts.USER_LOGIN)
                .setBlocked(TestConsts.VERIFY_PACKAGE_RESULT)
                .setUserPassword(TestConsts.USER_PASSWORD);

        //fill base model
        VerificatePackage verificatePackage = new VerificatePackage()
                .setLocalDate(TestConsts.VERIFY_PACKAGE_DATE)
                .setOperationUUID(TestConsts.VERIFY_PACKAGE_UUID)
                .setVerificateResult(TestConsts.VERIFY_PACKAGE_RESULT)
                .setUser(user);

        ModelMapper modelMapper = new ModelMapper();

        //map model to web model
        WebVerificatePackage webVerificatePackage = modelMapper.map(verificatePackage, WebVerificatePackage.class);
        modelMapper.validate();
        //map web model to new instance of model
        VerificatePackage convertedVerificatePackage = modelMapper.map(webVerificatePackage, VerificatePackage.class);
        modelMapper.validate();

        //assert equals params
        assertThat("isVerificateResult assertion", convertedVerificatePackage.isVerificateResult(), equalTo(TestConsts.VERIFY_PACKAGE_RESULT));
        assertThat("getLocalDate assertion", convertedVerificatePackage.getLocalDate(), equalTo(TestConsts.VERIFY_PACKAGE_DATE));
        assertThat("getOperationUUID assertion", convertedVerificatePackage.getOperationUUID(), equalTo(TestConsts.VERIFY_PACKAGE_UUID));

        User convertedUser = convertedVerificatePackage.getUser();
        assertThat("getUserPassword assertion", convertedUser.getUserPassword(), equalTo(TestConsts.USER_PASSWORD));
        assertThat("getUserLogin assertion", convertedUser.getUserLogin(), equalTo(TestConsts.USER_LOGIN));
    }

    @Test
    public void TestUser() {
        //fill values
        UserEntity user = new UserEntity()
                .setUserLogin(TestConsts.USER_LOGIN)
                .setBlocked(TestConsts.VERIFY_PACKAGE_RESULT)
                .setId(TestConsts.USER_ID)
                .setUserPassword(TestConsts.USER_PASSWORD)
                .setFirstName(TestConsts.USER_FIRST_NAME)
                .setLastName(TestConsts.USER_LAST_NAME)
                .setMiddleName(TestConsts.USER_MIDDLE_NAME);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true);
        //map model to web model
        WebUser webUser = modelMapper.map(user, WebUser.class);
        modelMapper.validate();

        User newUser = modelMapper.map(webUser, User.class);
        modelMapper.validate();
    }

}
