package com.ecommerce.payment.application.mapper;

import com.ecommerce.payment.application.command.CreatePaymentCommand;
import com.ecommerce.payment.domain.payment.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

  Payment toDomain(CreatePaymentCommand command);

}
