package com.client.api.rasmooplus.service;

import com.client.api.rasmooplus.dto.UserDetailsDto;
import com.client.api.rasmooplus.model.jpa.UserCredentials;

public interface UserDetailsService {

    UserCredentials loadUserByUsernameAndPass(String username, String pass);

    void sendRecoveryCode(String email);

    boolean recoveryCodeIsValid(String recoveryCode, String email);

    void updatePasswordByRecoveryCode(UserDetailsDto userDetailsDto);

}
