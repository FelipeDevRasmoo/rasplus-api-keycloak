package com.client.api.rasmooplus.service;

import com.client.api.rasmooplus.dto.SubscriptionTypeDto;
import com.client.api.rasmooplus.model.jpa.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionType> findAll();

    SubscriptionType findById(Long id);

    SubscriptionType create(SubscriptionTypeDto dto);

    SubscriptionType update(Long id, SubscriptionTypeDto dto);

    void delete(Long id);
}