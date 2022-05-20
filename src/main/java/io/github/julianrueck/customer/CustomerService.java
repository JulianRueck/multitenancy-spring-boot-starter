package io.github.julianrueck.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    protected CustomerRepository customerRepository;

    public ResponseEntity<List<Customer>> getAllCustomers() {
        System.out.println("Listing all customers");
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable("id") long id) {
        System.out.println("Returning customer with the id: " + id);
        Optional<Customer> customer = customerRepository.findById(id);
        return new ResponseEntity<Optional<Customer>>(customer, HttpStatus.OK);
    }

    public Customer save(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    public Customer update(Customer newCustomer, long id) {
        return customerRepository.findById(id)
                .map(employee -> {
                    employee.setName(newCustomer.getName());
                    return customerRepository.save(employee);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }

    public void delete (long id){
        customerRepository.deleteById(id);
    }
}
