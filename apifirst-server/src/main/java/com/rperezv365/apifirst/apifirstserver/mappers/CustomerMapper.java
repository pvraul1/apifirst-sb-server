package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.Customer;
import com.rperezv365.apifirst.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    Customer customerDtoToCustomer(CustomerDto customerDto);

}
