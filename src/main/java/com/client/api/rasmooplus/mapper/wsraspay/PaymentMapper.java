package com.client.api.rasmooplus.mapper.wsraspay;

import com.client.api.rasmooplus.dto.wsraspay.CreditCardDto;
import com.client.api.rasmooplus.dto.wsraspay.PaymentDto;

public class PaymentMapper {

    public static PaymentDto build(String customerId, String orderId, CreditCardDto dto) {

        return PaymentDto.builder()
                .customerId(customerId)
                .orderId(orderId)
                .creditCard(dto)
                .build();

    }

}
