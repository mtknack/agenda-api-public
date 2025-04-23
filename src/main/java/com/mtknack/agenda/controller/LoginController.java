package com.mtknack.agenda.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mtknack.agenda.model.dto.LoginDTO;
import com.mtknack.agenda.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO login) {
        
        return ResponseEntity.ok(loginService.login(login));
    }
}
