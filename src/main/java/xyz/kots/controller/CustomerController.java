package xyz.kots.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @Api(description="Operations pertaining to customers")
    public class CustomerController {

        @Autowired
        private ICustomerService customerService;

        @ApiOperation(value = "Search a customer with an ID")
        @GetMapping("customer")
        public ResponseEntity<Customer> getCustomerById(@RequestParam("id") String id) {
            Customer customer = customerService.getCustomerId(Long.parseLong(id));
            if (customer == null)
                return new ResponseEntity(HttpStatus.GONE);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }

        @ApiOperation(value = "Search a customer with an NAME")
        @GetMapping("customers/{name}")
        public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable("name") String name) {
            List<Customer> listCustomers = customerService.getCustomerName(name);
            if (listCustomers.isEmpty())
                return new ResponseEntity(HttpStatus.GONE);
            return new ResponseEntity<List<Customer>>(listCustomers, HttpStatus.OK);


        }

        @ApiOperation(value = "View a list of customers")
        @GetMapping("customers")
        public ResponseEntity<List<Customer>> getAllCustomers() {
            List<Customer> list = customerService.getAllCustomers();
            return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
        }

        @ApiOperation(value = "Add customer")
        @PostMapping("customer")
        public ResponseEntity<Void> addCustomer(@RequestBody Customer customer, UriComponentsBuilder builder) {
            customerService.addCustomer(customer);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }

        @ApiOperation(value = "Update customer")
        @PutMapping("customer")
        public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
            customerService.updateCustomer(customer);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }

    }