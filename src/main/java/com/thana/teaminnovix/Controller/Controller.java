package com.thana.teaminnovix.Controller;

import com.thana.teaminnovix.Model.MigrantWorkerEntity;
import com.thana.teaminnovix.Model.UserEntity;
import com.thana.teaminnovix.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    UserService userService;

    @PostMapping("/register1")
    public String Register(@RequestBody UserEntity userEntity) {
        return userService.register(userEntity);
    }

    @PostMapping("/register2")
    public String Register2(@RequestBody MigrantWorkerEntity migrantWorkerEntity) {
        return userService.register2(migrantWorkerEntity);
    }

    @PostMapping("/verifyotp")
    public String VerifyOtp(@RequestBody OtpVerificationRequest otpVerificationRequest) {
        return userService.verifyOtp(otpVerificationRequest.getEmail(), otpVerificationRequest.getOtp());
    }
    @PostMapping("/login")
    public String Login(@RequestBody UserEntity userEntity) {
        return userService.Login(userEntity);
    }
}