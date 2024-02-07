package com.client.api.rasmooplus.service;

import com.client.api.rasmooplus.dto.PaymentProcessDto;

public interface PaymentInfoService {

    Boolean process(PaymentProcessDto dto);
}
