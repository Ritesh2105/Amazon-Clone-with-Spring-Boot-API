package com.example.amazoncloneapi.controllers;


import com.example.amazoncloneapi.CustomizedResponse;
import com.example.amazoncloneapi.models.Product;
import com.example.amazoncloneapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity getProducts() {

        var customizedResponse = new CustomizedResponse("A list of Products", service.getProducts());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/products/category")
    public ResponseEntity getProductsByCategory(@RequestParam(value = "category_id", defaultValue = "1001") String category_id) {

        var customizedResponse = new CustomizedResponse("A list of Products", service.getProductsByCategory(category_id));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/products/bestSeller")
    public ResponseEntity getProductsByBestSeller(@RequestParam(value = "bestSeller", defaultValue = "true") String bestSeller) {

        var customizedResponse = new CustomizedResponse("A list of Products based on bestSeller", service.getProductsByBestSeller(bestSeller));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getAProduct(@PathVariable("id") String id) {

        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Product with id :" + id, Collections.singletonList(service.getAProduct(id)));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteAProduct(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = null;
        try {
        service.deleteAProduct(id);
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/products", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addProduct(@RequestBody Product product) {

        service.insertIntoProducts(product);

        return new ResponseEntity(product, HttpStatus.OK);

    }

    @PutMapping(value = "/products/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editProduct(@PathVariable("id") String id, @RequestBody Product newProduct){
        CustomizedResponse customizedResponse = null;
        try {
        customizedResponse = new CustomizedResponse("Product with id" + id + "was updated succesfully", Collections.singletonList(service.editProduct(id, newProduct)));
    } catch (Exception e) {
        customizedResponse = new CustomizedResponse(e.getMessage(), null);
        return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
    }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    //CORS
}