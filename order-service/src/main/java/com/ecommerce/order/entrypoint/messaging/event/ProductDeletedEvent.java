package com.ecommerce.order.entrypoint.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDeletedEvent {

  private Long productId;

}
