package com.spendgo.dbproxy.controllers;

import com.spendgo.dbproxy.services.TokenService;
import com.spendgo.dbproxy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;


    @PostMapping("/token")
    public ResponseEntity<String> token(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body(tokenService.generateToken(authentication));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> bodyParams) {
        if (!bodyParams.containsKey("username") && !bodyParams.containsKey("password")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        String username = bodyParams.get("username");
        String password = bodyParams.get("password");
        if (userService.userExists(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        userService.createOrdinaryUser(username, password);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}

