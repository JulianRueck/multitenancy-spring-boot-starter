package com.example.multitenancy.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerDao.getAllCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) {
        System.out.println("the id: " + id);
        Customer customer = customerDao.getCustomer(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
}
