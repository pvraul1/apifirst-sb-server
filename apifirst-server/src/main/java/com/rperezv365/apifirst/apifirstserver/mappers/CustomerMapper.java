package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.Customer;
import com.rperezv365.apifirst.model.CustomerDto;
import com.rperezv365.apifirst.model.CustomerPatchDto;
import org.mapstruct.*;

@Mapper
@DecoratedWith(CustomerMapperDecorator.class)
public interface CustomerMapper {

    CustomerPatchDto customerToCustomerPatchDto(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "shipToAddress.id", ignore = true)
    @Mapping(target = "billToAddress.id", ignore = true)
    @Mapping(target = "paymentMethods", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchCustomer(CustomerPatchDto customerPatchDto, @MappingTarget Customer target);

    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    Customer customerDtoToCustomer(CustomerDto customerDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    void updateCustomer(CustomerDto customerDto, @MappingTarget Customer customer);

}
