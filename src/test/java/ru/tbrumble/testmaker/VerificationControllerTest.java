package ru.tbrumble.testmaker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import ru.tbrumble.testmaker.model.User;
import ru.tbrumble.testmaker.model.entity.UserEntity;
import ru.tbrumble.testmaker.model.web.WebVerificatePackage;
import ru.tbrumble.testmaker.repo.UserRepo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ru.tbrumble.testmaker.TestConsts.URL_VERIFICATOR;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VerificationControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    UserRepo userRepo;

    @BeforeEach
    public void prepareTestData() {
        userRepo.deleteAll();

        UserEntity user = new UserEntity();
        user.setUserPassword(TestConsts.USER_PASSWORD)
                .setUserLogin(TestConsts.USER_LOGIN)
                .setBlocked(TestConsts.USER_IS_BLOCKED)
                .setFirstName(TestConsts.USER_FIRST_NAME)
                .setLastName(TestConsts.USER_LAST_NAME)
                .setMiddleName(TestConsts.USER_MIDDLE_NAME);
        userRepo.save(user);
    }

    @AfterEach
    public void deleteTestData() {
        userRepo.deleteAll();
    }

    @Test
    public void testVerifyPackage() {
        WebVerificatePackage webVerificatePackage = new WebVerificatePackage();
        webVerificatePackage.setVerificateResult(Boolean.FALSE);
        webVerificatePackage.setUser(
                new User().setUserLogin(TestConsts.USER_LOGIN)
                .setBlocked(Boolean.FALSE)
                .setUserPassword(TestConsts.USER_PASSWORD)
                .setFirstName(TestConsts.USER_FIRST_NAME)
                .setLastName(TestConsts.USER_LAST_NAME)
                .setMiddleName(TestConsts.USER_MIDDLE_NAME)
        );

        String url = String.format(URL_VERIFICATOR, port);
        ResponseEntity<WebVerificatePackage> responseEntity = restTemplate.postForEntity(url,webVerificatePackage,WebVerificatePackage.class);
        assertThat("testVerifyPackage. get response", responseEntity, notNullValue());
        assertThat("testVerifyPackage. response code", responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat("testVerifyPackage. response body 1", responseEntity.hasBody(), equalTo(Boolean.TRUE));
        assertThat("testVerifyPackage. response body 2", responseEntity.getBody(), notNullValue());

        WebVerificatePackage returnedPackage = responseEntity.getBody();

        assertThat("testVerifyPackage. returned UUID", returnedPackage.getOperationUUID(), notNullValue());
        assertThat("testVerifyPackage. returned localDate", returnedPackage.getLocalDate(), notNullValue());
        assertThat("testVerifyPackage. returned result", returnedPackage.isVerificateResult(), equalTo(Boolean.TRUE));
    }
}
