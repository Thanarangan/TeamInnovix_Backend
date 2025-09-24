package com.thana.teaminnovix.HealthIdGenerator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdGenerator {

    public String GenerateId(){
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }
}
