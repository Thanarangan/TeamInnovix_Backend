package com.thana.teaminnovix.Model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginRequest {
    private String userName;
    private String password;
}
