package ru.tbrumble.testmaker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import ru.tbrumble.testmaker.model.entity.UserEntity;
import ru.tbrumble.testmaker.model.web.WebUser;
import ru.tbrumble.testmaker.repo.UserRepo;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ru.tbrumble.testmaker.TestConsts.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
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
    public void blockUser() {
        String requestJson = "";
        String url = String.format(URL_BLOCK_USER, port, TestConsts.USER_LOGIN);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,entity, String.class);
        assertThat("blockUser. get response", responseEntity, notNullValue());
        assertThat("blockUser. response code", responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat("blockUser. response body 1", responseEntity.hasBody(), notNullValue());
        assertThat("blockUser. response body 2", responseEntity.getBody(), notNullValue());

        boolean isBlocked = userRepo.getByUserLogin(TestConsts.USER_LOGIN).isBlocked();
        boolean returnedValue = Boolean.valueOf(responseEntity.getBody());

        assertThat("blockUser. response value 1", isBlocked, equalTo(Boolean.TRUE));
        assertThat("blockUser. response value 2", returnedValue, equalTo(Boolean.TRUE));
    }

    @Test
    public void unblockUser() {
        blockUser();

        String requestJson = "";
        String url = String.format(URL_UNBLOCK_USER, port, TestConsts.USER_LOGIN);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,entity, String.class);
        assertThat("unblockUser. get response", responseEntity, notNullValue());
        assertThat("unblockUser. response code", responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat("unblockUser. response body 1", responseEntity.hasBody(), notNullValue());
        assertThat("unblockUser. response body 2", responseEntity.getBody(), notNullValue());

        boolean isBlocked = userRepo.getByUserLogin(TestConsts.USER_LOGIN).isBlocked();
        boolean returnedValue = Boolean.valueOf(responseEntity.getBody());

        assertThat("unblockUser. response value 1", isBlocked, equalTo(Boolean.FALSE));
        assertThat("unblockUser. response value 2", returnedValue, equalTo(Boolean.FALSE));
    }

    @Test
    public void verifyHashPassword() {
        String requestJson = "";
        String url = String.format(URL_COMPARE_PASSWORD_USER, port, TestConsts.USER_LOGIN, USER_PASSWORD);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,entity, String.class);
        assertThat("verifyHashPassword. get response", responseEntity, notNullValue());
        assertThat("verifyHashPassword. response code", responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat("verifyHashPassword. response body 1", responseEntity.hasBody(), notNullValue());
        assertThat("verifyHashPassword. response body 2", responseEntity.getBody(), notNullValue());
        assertThat("verifyHashPassword. response body value", Boolean.valueOf(responseEntity.getBody()), equalTo(Boolean.TRUE));
    }

    @Test
    public void getAllAndCompareData() {
        ResponseEntity<WebUser[]> response = restTemplate.getForEntity(
                String.format(URL_GET_ALL, port), WebUser[].class);
        assertThat("getAllAndCompareData. get response", response != null);
        assertThat("getAllAndCompareData. response code", response.getStatusCode() == HttpStatus.OK);
        assertThat("getAllAndCompareData. response body", response.hasBody() && (response.getBody() != null));
        assertThat("getAllAndCompareData. response body list count", response.getBody().length == userRepo.count());
        checkUserData(Arrays.stream(response.getBody()).findAny().get());
    }

    private void checkUserData(WebUser webUser) {
        assertThat("checkUserData login", webUser.getUserLogin(), equalTo(TestConsts.USER_LOGIN));
        assertThat("checkUserData password", webUser.getUserPassword(), equalTo(TestConsts.USER_PASSWORD));
        assertThat("checkUserData password", webUser.getFirstName(), equalTo(TestConsts.USER_FIRST_NAME));
        assertThat("checkUserData password", webUser.getLastName(), equalTo(TestConsts.USER_LAST_NAME));
        assertThat("checkUserData password", webUser.getMiddleName(), equalTo(TestConsts.USER_MIDDLE_NAME));
        assertThat("checkUserData password", webUser.isBlocked(), equalTo(TestConsts.USER_IS_BLOCKED));
    }

}
