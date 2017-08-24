package xyz.kots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.kots.entity.Customer;
import xyz.kots.service.ICustomerService;

import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("customer")
    public ResponseEntity<Customer> getCustomerById(@RequestParam("id") String id) {
        Customer customer= customerService.getCustomerId(Integer.parseInt(id));
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping("customers/{name}")
    public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable("name") String name) {
        List<Customer>  list = customerService.getCustomerName(name);
        return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
    }

    @GetMapping("customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> list = customerService.getAllCustomers();
        return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
    }

    @PostMapping("customer")
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer, UriComponentsBuilder builder) {
        customerService.addCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("customer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
}
