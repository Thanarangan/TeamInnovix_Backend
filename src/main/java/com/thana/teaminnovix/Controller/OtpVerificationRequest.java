package com.thana.teaminnovix.Controller;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerificationRequest {
    private String email;
    private String otp;
}
