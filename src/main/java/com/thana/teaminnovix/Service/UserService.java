package com.thana.teaminnovix.Service;

import com.thana.teaminnovix.EmailOtpSendVerify.OtpController;
import com.thana.teaminnovix.HealthIdGenerator.IdGenerator;
import com.thana.teaminnovix.Model.MigrantWorkerEntity;
import com.thana.teaminnovix.Model.UserEntity;
import com.thana.teaminnovix.Repo.MigrantWorkerRepo;
import com.thana.teaminnovix.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    MigrantWorkerRepo migrantWorkerRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    OtpController otpController;

    Map<String, UserEntity> pendinguser = new ConcurrentHashMap<>();

    public String register(UserEntity userEntity) {
        boolean exists = userRepo.findByUserName(userEntity.getUserName()).isPresent();

        if (exists) {
            return "Username already exists";
        } else {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity.setHealthId(idGenerator.GenerateId());
            System.out.println(otpController.sendOtp(userEntity.getEmail()));
            pendinguser.put(userEntity.getEmail(), userEntity);
            return "Otp has been sent to your email Plz verify it before for register "+ userEntity.getEmail();
        }
    }

    public String register2(MigrantWorkerEntity migrantWorkerEntity){
        Optional<UserEntity> user = userRepo.findByUserName(migrantWorkerEntity.getUsername());
        migrantWorkerEntity.setHealthId(user.get().getHealthId());
        migrantWorkerRepo.save(migrantWorkerEntity);
        return "Worker details Registered Successfully";
    }


    public String verifyOtp(String email, String otp) {
        boolean isValid = otpController.verifyOtp(email, otp);
        UserEntity user = pendinguser.remove(email);
        if(isValid){
            userRepo.save(user);
            return "Otp has been verified and Registered Successfully";
        }
        else{
            return "Inavlid Otp";
        }
    }
    public String Login(UserEntity userEntity) {
        Optional<UserEntity> user = userRepo.findByUserName(userEntity.getUserName());
        if(user.isPresent()) {
            if(passwordEncoder.matches(userEntity.getPassword(), user.get().getPassword())) {
                return "Login successful";
            }
            else{
                return "Wrong Password";
            }
        }
        else{
            return "Username not found";
        }
    }

}
