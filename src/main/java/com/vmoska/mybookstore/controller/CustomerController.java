package com.vmoska.mybookstore.controller;

import com.vmoska.mybookstore.model.dto.CustomerDto;
import com.vmoska.mybookstore.model.entity.Customer;
import com.vmoska.mybookstore.service.impl.CustomerServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vp1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerServiceImpl customerService;


    @GetMapping("/{id}")
    @ApiOperation(value = "Get customer", notes = "Get customer by id")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
        log.info("Getting customer with id: {}", id);
        CustomerDto customerById = customerService.findCustomerById(id);
        return new ResponseEntity<>(customerById, HttpStatus.OK);
    }

    @GetMapping("/get-customers")
    @ApiOperation(value = "Get customers", notes = "Get all customers")
    public ResponseEntity<Iterable<CustomerDto>> getCustomers() {
        log.info("Getting all customers");
        Iterable<CustomerDto> customers = customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/add-customer")
    @ApiOperation(value = "Add customer", notes = "Add customer")
    public ResponseEntity<CustomerDto> createCustomer(@Validated @RequestBody Customer customer) {
        log.info("Creating customer: {}", customer);
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping("/update-customer/{id}")
    @ApiOperation(value = "Update customer", notes = "Update customer by id")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @Validated @RequestBody Customer customer) {
        log.info("Updating customer: {}", customer);
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
    }

    @DeleteMapping("/delete-customer/{id}")
    @ApiOperation(value = "Delete customer", notes = "Delete customer by id")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable Long id) {
        log.info("Deleting customer with id: {}", id);
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }
}
