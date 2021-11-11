package ru.tbrumble.testmaker;

import java.time.LocalDate;
import java.util.UUID;

public final class TestConsts {
    public static final String USER_LOGIN = "test_login";
    public static final Long USER_ID = 1l;
    public static final String USER_PASSWORD = "608b6e7f14f67f99523a67c936eb8347";
    public static final String USER_LAST_NAME = "TESTOV";
    public static final String USER_FIRST_NAME = "TEST";
    public static final String USER_MIDDLE_NAME = "TESTOVICH";
    public static final boolean USER_IS_BLOCKED = Boolean.FALSE;


    public static final LocalDate VERIFY_PACKAGE_DATE = LocalDate.now();
    public static final UUID VERIFY_PACKAGE_UUID = UUID.randomUUID();
    public static final boolean VERIFY_PACKAGE_RESULT = Boolean.TRUE;



    public static final String URL_GET_ALL = "http://localhost:%s/user/get-all";
    public static final String URL_BLOCK_USER = "http://localhost:%s/user/block-by-name/login/%s";
    public static final String URL_UNBLOCK_USER = "http://localhost:%s/user/unblock-by-name/login/%s";
    public static final String URL_COMPARE_PASSWORD_USER = "http://localhost:%s/user/compare-user-password/login/%s/hashPassword/%s";
    public static final String URL_VERIFICATOR = "http://localhost:%s/verificator/verify-by-user/";


}
