package com.thana.teaminnovix.EmailOtpSendVerify;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class OtpGenerate {
    SecureRandom random = new SecureRandom();
    int otplen = 6;

    public String GenerateOtp(){
        StringBuilder ob = new StringBuilder();
        for (int i = 0; i < otplen; i++) {
            ob.append(random.nextInt(10));
        }
        return ob.toString();
    }
}