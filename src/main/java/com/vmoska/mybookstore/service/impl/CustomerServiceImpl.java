package com.vmoska.mybookstore.service.impl;

import com.vmoska.mybookstore.exception.BookNotExistsException;
import com.vmoska.mybookstore.exception.CustomerEmailAlreadyExistsException;
import com.vmoska.mybookstore.exception.NotFoundException;
import com.vmoska.mybookstore.model.entity.Customer;
import com.vmoska.mybookstore.model.dto.CustomerDto;
import com.vmoska.mybookstore.model.mapper.CustomerMapper;
import com.vmoska.mybookstore.repository.CustomerRepository;
import com.vmoska.mybookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public CustomerDto addCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerEmailAlreadyExistsException("Email " + customer + " already exists!");
        } else {
            Customer save = customerRepository.save(new Customer(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail()
            ));
            return customerMapper.customerToCustomerDto(save);
        }


    }

    @Override
    public CustomerDto findCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer " + customerId + " not found!"));
        return customerMapper.customerToCustomerDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(Customer customer, Long customerId) {
        return customerRepository.findById(customerId)
                .map(c -> {
                    c.setFirstName(customer.getFirstName());
                    c.setLastName(customer.getLastName());
                    c.setEmail(customer.getEmail());
                    return customerMapper.customerToCustomerDto(c);
                })
                .orElseThrow(() -> new NotFoundException("Customer " + customerId + " not found!"));
    }

    @Override
    public Iterable<CustomerDto> getCustomers() {
        return customerMapper.customersToCustomerDtos(customerRepository.findAll());
    }


    @Override
    public CustomerDto deleteCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .map(b -> {
                    customerRepository.delete(b);
                    return customerMapper.customerToCustomerDto(b);
                }).orElseThrow(() -> new BookNotExistsException("Customer " + customerId + " not exists!"));
    }

}
