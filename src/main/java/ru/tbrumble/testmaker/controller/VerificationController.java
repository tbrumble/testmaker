package ru.tbrumble.testmaker.controller;

//import io.springfox.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import ru.tbrumble.testmaker.model.web.WebVerificatePackage;
import ru.tbrumble.testmaker.services.VerificationService;


@RestController
@RequestMapping("/verificator")
public class VerificationController {
    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

 //   @ApiOperation("Верификация пользователя")
    @PostMapping("/verify-by-user/")
    public WebVerificatePackage verifyUserData(
   //         @ApiParam(value = "Пакет верификации", required = true)
            @RequestBody WebVerificatePackage webVerificatePackage) {
        return verificationService.verifyUserData(webVerificatePackage);
    }

    @GetMapping("/example")
    public WebVerificatePackage getExampleJson() {
        return verificationService.getExample();
    }
}
