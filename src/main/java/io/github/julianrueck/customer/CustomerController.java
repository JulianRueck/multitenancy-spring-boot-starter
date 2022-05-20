package io.github.julianrueck.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable("id") long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public Customer newCustomer(@RequestBody Customer newCustomer) {
        return customerService.save(newCustomer);
    }

    @PutMapping("customers/{id}")
    public Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable long id) {
        return customerService.update(newCustomer, id);
    }

    @DeleteMapping("customers/{id}")
    public void deleteCustomer (@PathVariable long id) {
        customerService.delete(id);
    }

}
