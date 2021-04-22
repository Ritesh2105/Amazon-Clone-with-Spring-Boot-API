package com.example.amazoncloneapi.controllers;

import com.example.amazoncloneapi.CustomizedResponse;
import com.example.amazoncloneapi.models.Customer;
import com.example.amazoncloneapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController {

    @Autowired
    private  CustomerService service;

    @GetMapping("/customers")
    public ResponseEntity getCustomers()
    {

        var customizedResponse = new CustomizedResponse("A list of customers",service.getCustomers());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/customers/{username}")
    public ResponseEntity getACustomer(@PathVariable("username") String username)
    {

        var customizedResponse = new CustomizedResponse("Customer with username :"+username, Collections.singletonList(service.getACustomer(username)));

        return new ResponseEntity(customizedResponse,HttpStatus.OK);
    }

    @PostMapping(value = "/customers",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addCustomer(@RequestBody Customer customer)
    {
        service.insertIntoCustomers(customer);

        return  new ResponseEntity(customer,HttpStatus.OK);

    }

    @DeleteMapping("/customers/{username}")
    public ResponseEntity deleteACustomer(@PathVariable("username") String username)
    {
        service.deleteACustomer(username);

        return new ResponseEntity(HttpStatus.OK);
    }


    //CORS



}
