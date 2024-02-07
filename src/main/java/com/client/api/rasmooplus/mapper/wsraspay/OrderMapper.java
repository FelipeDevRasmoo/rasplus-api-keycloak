package com.client.api.rasmooplus.mapper.wsraspay;

import com.client.api.rasmooplus.dto.PaymentProcessDto;
import com.client.api.rasmooplus.dto.wsraspay.OrderDto;

public class OrderMapper {

    public static OrderDto build(String customerId, PaymentProcessDto paymentProcessDto) {
        return OrderDto.builder()
                .customerId(customerId)
                .productAcronym(paymentProcessDto.getProductKey())
                .discount(paymentProcessDto.getDiscount())
                .build();
    }
}
