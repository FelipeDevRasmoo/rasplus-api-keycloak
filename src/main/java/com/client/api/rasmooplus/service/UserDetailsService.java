package com.client.api.rasmooplus.service;

import com.client.api.rasmooplus.dto.UserDetailsDto;

public interface UserDetailsService {


    void sendRecoveryCode(String email);

    boolean recoveryCodeIsValid(String recoveryCode, String email);

    void updatePasswordByRecoveryCode(UserDetailsDto userDetailsDto);

}
