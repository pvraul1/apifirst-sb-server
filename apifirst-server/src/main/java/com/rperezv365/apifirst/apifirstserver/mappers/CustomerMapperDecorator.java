package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.Customer;
import com.rperezv365.apifirst.model.CustomerDto;
import com.rperezv365.apifirst.model.CustomerPatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * CustomerMapperDecorator
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 18/01/2025 - 20:32
 * @since 1.17
 */
public abstract class CustomerMapperDecorator implements CustomerMapper {

    @Autowired
    @Qualifier("delegate")
    private CustomerMapper delegate;

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Override
    public CustomerPatchDto customerToCustomerPatchDto(Customer customer) {
        return delegate.customerToCustomerPatchDto(customer);
    }

    @Override
    public void patchCustomer(CustomerPatchDto customerPatchDto, Customer target) {
        delegate.patchCustomer(customerPatchDto, target);

        if (customerPatchDto.getPaymentMethods() != null) {
            customerPatchDto.getPaymentMethods().forEach(paymentMethodPatchDto -> target.getPaymentMethods().forEach(paymentMethod -> {
                if (paymentMethod.getId().equals(paymentMethodPatchDto.getId())) {
                    paymentMethodMapper.updatePaymentMethod(paymentMethodPatchDto, paymentMethod);
                }
            }));
        }
    }

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        return delegate.customerToCustomerDto(customer);
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        return delegate.customerDtoToCustomer(customerDto);
    }

    @Override
    public void updateCustomer(CustomerDto customerDto, Customer customer) {
        delegate.updateCustomer(customerDto, customer);
    }

}
