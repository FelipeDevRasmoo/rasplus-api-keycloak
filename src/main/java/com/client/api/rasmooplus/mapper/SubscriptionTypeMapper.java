package com.client.api.rasmooplus.mapper;

import com.client.api.rasmooplus.dto.SubscriptionTypeDto;
import com.client.api.rasmooplus.model.jpa.SubscriptionType;

public class SubscriptionTypeMapper {

    public static SubscriptionType fromDtoToEntity(SubscriptionTypeDto dto) {
        return SubscriptionType.builder()
                .id(dto.getId())
                .name(dto.getName())
                .accessMonths(dto.getAccessMonths())
                .price(dto.getPrice())
                .productKey(dto.getProductKey())
                .build();
    }

}
