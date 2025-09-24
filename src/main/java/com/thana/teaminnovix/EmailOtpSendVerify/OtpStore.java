package com.thana.teaminnovix.EmailOtpSendVerify;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OtpStore {
    private Map<String, OTPData> otpstorage = new ConcurrentHashMap<>();

    public void StoreOtp(String email, String otp){
        otpstorage.put(email, new OTPData(otp, LocalDateTime.now().plusMinutes(10)));
    }

    public boolean verifyOtp(String email, String otp){
        OTPData data = otpstorage.get(email);

        if (data == null) {
            return false;
        }

        if(LocalDateTime.now().isAfter(data.getExpiryTime())){
            otpstorage.remove(email);
            return false;
        }

        if(data.getOtp().equals(otp)){
            otpstorage.remove(email);
            return true;
        }
        return false;
    }

    static class OTPData {
        private String otp;
        private LocalDateTime expiryTime;

        public OTPData(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }

        public String getOtp() {
            return otp;
        }

        public LocalDateTime getExpiryTime() {
            return expiryTime;
        }
    }
}
