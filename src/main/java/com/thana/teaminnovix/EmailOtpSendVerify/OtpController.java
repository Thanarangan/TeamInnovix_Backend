package com.thana.teaminnovix.EmailOtpSendVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OtpController {

    @Autowired
    EmailService emailService;

    @Autowired
    OtpStore otpStore;

    @Autowired
    OtpGenerate  otpGenerate;

    public String sendOtp(@RequestBody String email) {
        String otp = otpGenerate.GenerateOtp();
        otpStore.StoreOtp(email, otp);
        emailService.SendOtpEmail(email, otp);
        return "Otp has been sent to" + email;
    }

    public boolean verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpStore.verifyOtp(email, otp);
        if (isValid) {
            return true;
        }
        else{
            return false;
        }
    }
}
