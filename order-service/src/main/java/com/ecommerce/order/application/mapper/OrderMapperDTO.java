package com.ecommerce.order.application.mapper;

import com.ecommerce.order.application.dto.response.OrderResponse;
import com.ecommerce.order.domain.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapperDTO {

  OrderResponse toOrderCreatedResponse(Order order);

}
