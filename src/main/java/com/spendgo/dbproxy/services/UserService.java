package com.spendgo.dbproxy.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final JdbcUserDetailsManager userDetailsMgr;
    private final PasswordEncoder passwordEncoder;

    public UserService(JdbcUserDetailsManager userDetailsMgr, PasswordEncoder passwordEncoder) {
        this.userDetailsMgr = userDetailsMgr;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userExists(String username) {
        return this.userDetailsMgr.userExists(username);
    }

    public void createOrdinaryUser(String username, String password) {
        this.createUser(username, password, "ROLE_USER");
    }

    public void createAdminUser(String username, String password) {
        this.createUser(username, password, "ROLE_ADMIN");
    }

    public void createUser(String username, String password, String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        User user = new User(username, this.passwordEncoder.encode(password), authorities);
        this.userDetailsMgr.createUser(user);
    }

}
