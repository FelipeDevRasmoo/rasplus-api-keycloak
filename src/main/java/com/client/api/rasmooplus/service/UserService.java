package com.client.api.rasmooplus.service;

import com.client.api.rasmooplus.dto.UserDto;
import com.client.api.rasmooplus.model.jpa.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    User create(UserDto dto);

    User uploadPhoto(Long id, MultipartFile file) throws IOException;

    byte[] downloadPhoto(Long id);
}
