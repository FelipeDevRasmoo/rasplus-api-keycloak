package com.client.api.rasmooplus.service;

import com.client.api.rasmooplus.dto.UserDetailsDto;
import com.client.api.rasmooplus.dto.oauth.UserRepresentationDto;

public interface UserDetailsService {


    void sendRecoveryCode(String email);

    boolean recoveryCodeIsValid(String recoveryCode, String email);

    void updatePasswordByRecoveryCode(UserDetailsDto userDetails);

    void createAuthUser(UserRepresentationDto userRepresentation);

    void updateAuthUser(UserRepresentationDto userRepresentation, String email);
}
