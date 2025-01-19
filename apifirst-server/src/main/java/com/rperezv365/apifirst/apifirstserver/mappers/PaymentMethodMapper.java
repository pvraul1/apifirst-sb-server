package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.PaymentMethod;
import com.rperezv365.apifirst.model.CustomerPaymentMethodPatchDto;
import com.rperezv365.apifirst.model.PaymentMethodDto;
import org.mapstruct.*;

@Mapper
public interface PaymentMethodMapper {

    PaymentMethodDto paymentMethodToPaymentMethodDto(PaymentMethod paymentMethod);

    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updatePaymentMethod(CustomerPaymentMethodPatchDto customerPaymentMethodPatchDto,
                             @MappingTarget PaymentMethod paymentMethod);

}
