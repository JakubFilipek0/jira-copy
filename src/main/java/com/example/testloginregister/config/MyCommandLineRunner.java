package com.example.testloginregister.config;

import com.example.testloginregister.repository.AppUserRepository;
import com.example.testloginregister.user.AppUser;
import com.example.testloginregister.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public MyCommandLineRunner(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        AppUser adminUser = AppUser.builder()
                .firstname("Admin")
                .lastname("User")
                .email("admin@example.com")
                .password(passwordEncoder.encode("adminPassword"))
                .role(Role.ADMIN)
                .build();
        appUserRepository.save(adminUser);
        System.out.println("ADMIN HAS BEEN CREATED:");
        System.out.println(adminUser);
    }
}
