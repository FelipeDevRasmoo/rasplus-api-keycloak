package com.client.api.rasmooplus.service;

import com.client.api.rasmooplus.dto.LoginDto;

public interface AuthenticationService {

    String auth(LoginDto dto);


}
