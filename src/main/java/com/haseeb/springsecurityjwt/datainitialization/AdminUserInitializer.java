package com.haseeb.springsecurityjwt.datainitialization;

import com.haseeb.springsecurityjwt.entity.User;
import com.haseeb.springsecurityjwt.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner createAdminUser(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if(userRepo.findByUsername("admin") == null){
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("test123"));
                admin.setRole("ROLE_ADMIN");
                userRepo.save(admin);
                System.out.println("Admin user created!");
            }
        };
    }
}
