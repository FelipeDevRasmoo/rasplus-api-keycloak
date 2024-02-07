package com.client.api.rasmooplus.service.impl;

import com.client.api.rasmooplus.model.jpa.UserType;
import com.client.api.rasmooplus.repositoy.jpa.UserTypeRepository;
import com.client.api.rasmooplus.service.UserTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;

    UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }
}
