package com.example.amazoncloneapi.services;

import com.example.amazoncloneapi.models.Product;
import com.example.amazoncloneapi.models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Product> getProducts() {
        //business logic
        return productRepository.findAll();

    }

     public void insertIntoProducts(Product product) {

        productRepository.insert(product);
    }

    public Optional<Product> getAProduct(String id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new Exception("Product with "+ id + " is not found");
        }

        return product;
    }

    public String deleteAProduct(String id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new Exception("Product with "+ id + " is not found");
        }
        productRepository.deleteById(id);
        return "Deleted";
    }

    public List<Product> getProductsByCategory(String category_id) {
        //business logic
        Query query = new Query();
        query.addCriteria(Criteria.where("category_id").is(category_id));
        List<Product> products = mongoTemplate.find(query, Product.class);
        return products;
    }

    public List<Product> getProductsByBestSeller(String bestSeller) {
        //business logic
        Query query = new Query();
        query.addCriteria(Criteria.where("bestSeller").is(bestSeller));
        List<Product> products = mongoTemplate.find(query, Product.class);
        return products;
    }

    public Product editProduct(String id, Product newProductData) throws Exception {

        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new Exception("Product with "+ id + " is not found");
        }
        product.get().setProduct_id(newProductData.getProduct_id());
        product.get().setCategory_id(newProductData.getCategory_id());
        product.get().setTitle(newProductData.getTitle());
        product.get().setPoster(newProductData.getPoster());
        product.get().setDescription(newProductData.getDescription());
        product.get().setPrice(newProductData.getPrice());
        product.get().setBestSeller(newProductData.getBestSeller());
        Product updatedProduct = productRepository.save(product.get());

        return updatedProduct;
    }

}