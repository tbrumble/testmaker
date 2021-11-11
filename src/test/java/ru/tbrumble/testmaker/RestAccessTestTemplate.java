package ru.tbrumble.testmaker;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import ru.tbrumble.testmaker.model.entity.SwitchEntity;
import ru.tbrumble.testmaker.repo.SwitchRepo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAccessTestTemplate {
    private final static String URL_CONST = "http://localhost:%s/%s";

    private String urlRestPath;
    private String switchName;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    SwitchRepo switchRepo;

    public RestAccessTestTemplate(String urlRestPath, String switchName) {
        this.urlRestPath = urlRestPath;
        this.switchName = switchName;
    }

    @BeforeEach
    public void beforeTest() {
        switchRepo.deleteAll();
    }

    @AfterEach
    public void AfterTest() {
        switchRepo.deleteAll();
    }


    //@Test
    public void testRestWithAccess() {
        switchRepo.save(new SwitchEntity()
                .setSwitchName(switchName)
                .setValue(Boolean.TRUE)
        );

        String requestJson = "";
        String url = String.format(URL_CONST, port, urlRestPath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        MatcherAssert.assertThat("testRestWithAccess. get response", responseEntity, Matchers.notNullValue());
        MatcherAssert.assertThat("testRestWithAccess. response code", responseEntity.getStatusCode(), Matchers.equalTo(HttpStatus.OK));
    }

    //@Test
    public void testRestWithoutAccess() {
        switchRepo.save(new SwitchEntity()
                .setSwitchName(switchName)
                .setValue(Boolean.FALSE)
        );

        String requestJson = "";
        String url = String.format(URL_CONST, port, urlRestPath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        MatcherAssert.assertThat("testRestWithAccess. get response", responseEntity, Matchers.notNullValue());
        MatcherAssert.assertThat("testRestWithAccess. response code", responseEntity.getStatusCode(), Matchers.equalTo(HttpStatus.NOT_FOUND));
    }
}
