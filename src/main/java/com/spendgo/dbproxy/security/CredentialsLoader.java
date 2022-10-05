package com.spendgo.dbproxy.security;

import com.spendgo.dbproxy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class CredentialsLoader implements ApplicationRunner {
    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;
    @Autowired
    private UserService userService;


    @Override
    public void run(ApplicationArguments args) {
        if (userService.userExists(adminUsername)) return;
        userService.createAdminUser(adminUsername, adminPassword);
    }
}
