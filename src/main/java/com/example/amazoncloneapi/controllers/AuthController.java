package com.example.amazoncloneapi.controllers;

import com.example.amazoncloneapi.CustomizedResponse;
import com.example.amazoncloneapi.models.Customer;
import com.example.amazoncloneapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/auth", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody Customer customer) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(),customer.getPassword()));

            var customizedResponse = new CustomizedResponse("You Logged In successfully",null);

            return new ResponseEntity(customizedResponse,HttpStatus.OK);
        }
        catch (BadCredentialsException e){
            var customizedResponse = new CustomizedResponse("Username or password is incorrect",null);

            return new ResponseEntity(customizedResponse,HttpStatus.UNAUTHORIZED);
        }
    }
}


