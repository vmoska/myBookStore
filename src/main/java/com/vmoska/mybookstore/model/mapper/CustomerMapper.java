package com.vmoska.mybookstore.model.mapper;

import com.vmoska.mybookstore.model.dto.CustomerDto;
import com.vmoska.mybookstore.model.entity.Customer;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToCustomerDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer updateCustomerFromCustomerDto(CustomerDto customerDto, @MappingTarget Customer customer);

    Iterable<CustomerDto> customersToCustomerDtos(List<Customer> all);
}
