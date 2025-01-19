package com.rperezv365.apifirst.apifirstserver.mappers;

import com.rperezv365.apifirst.apifirstserver.domain.Order;
import com.rperezv365.apifirst.model.OrderCreateDto;
import com.rperezv365.apifirst.model.OrderDto;
import com.rperezv365.apifirst.model.OrderPatchDto;
import com.rperezv365.apifirst.model.OrderUpdateDto;
import org.mapstruct.*;

@Mapper
@DecoratedWith(OrderMapperDecorator.class)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "shipmentInfo", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "selectedPaymentMethod", ignore = true)
    @Mapping(target = "orderLines", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchOrder(OrderPatchDto orderPatchDto, @MappingTarget Order target);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "shipmentInfo", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "selectedPaymentMethod", ignore = true)
    @Mapping(target = "orderLines", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    void updateOrder(OrderUpdateDto orderDto, @MappingTarget Order order);

    @Mapping(target = "selectPaymentMethodId", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    OrderUpdateDto orderToUpdateDto(Order order);

    @Mapping(target = "shipmentInfo", ignore = true)
    @Mapping(target = "selectedPaymentMethod", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Order orderCreateDtoToOrder(OrderCreateDto orderCreateDto);

    @Mapping(target = "selectedPaymentMethod", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    Order orderDtoToOrder(OrderDto orderDto);

    OrderDto orderToOrderDto(Order order);
}
