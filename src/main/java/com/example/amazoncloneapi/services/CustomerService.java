package com.example.amazoncloneapi.services;

import com.example.amazoncloneapi.models.Customer;
import com.example.amazoncloneapi.models.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Customer> getCustomers()
    {
        //business logic
        return customerRepository.findAll();

    }

    public Customer insertIntoCustomers(Customer customer)
    {
        String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        Customer customer1 = customerRepository.insert(customer);
        return customer1;
    }

    public Optional<Customer> getACustomer(String username)
    {
        return customerRepository.findById(username);
    }

    public void deleteACustomer(String username)
    {
        customerRepository.deleteById(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer foundCustomer = customerRepository.findByUsername(username);

        String userName =foundCustomer.getUsername();
        String password = foundCustomer.getPassword();

        return new User(userName,password,new ArrayList<>());

    }
}
