package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.PaymentMethod;
import com.rperezv365.apifirst.model.PaymentMethodDto;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentMethodMapper {

    PaymentMethodDto paymentMethodToPaymentMethodDto(PaymentMethod paymentMethod);

}
