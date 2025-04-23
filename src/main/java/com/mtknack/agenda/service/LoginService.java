package com.mtknack.agenda.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mtknack.agenda.Exceptions.DTOs.CustomException;
import com.mtknack.agenda.model.dto.LoginDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    
    public LoginDTO login(LoginDTO login){

        // Temporario
        if((login.getName().equals( "mtk")) && (login.getPassword().equals("123"))){
            return login;
        }else{
            throw new CustomException("Incorrect username or password", HttpStatus.NOT_FOUND, "Login cannot be performed.");
        }
    }
}
