package ru.tbrumble.testmaker.services;

import ru.tbrumble.testmaker.model.web.WebVerificatePackage;

public interface VerificationService {
    WebVerificatePackage verifyUserData(WebVerificatePackage webVerificatePackage);
    WebVerificatePackage getExample();
}
