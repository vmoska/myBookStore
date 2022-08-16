package com.vmoska.mybookstore.service;

import com.vmoska.mybookstore.model.entity.Customer;
import com.vmoska.mybookstore.model.dto.CustomerDto;

public interface CustomerService {
    CustomerDto addCustomer(Customer customer);

    CustomerDto findCustomerById(Long customerId);

    CustomerDto updateCustomer(Customer customer, Long customerId);

    Iterable<CustomerDto> getCustomers();

    CustomerDto deleteCustomer(Long customerId);
}

