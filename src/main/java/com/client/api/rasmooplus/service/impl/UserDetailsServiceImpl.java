package com.client.api.rasmooplus.service.impl;

import com.client.api.rasmooplus.dto.UserDetailsDto;
import com.client.api.rasmooplus.exception.NotFoudException;
import com.client.api.rasmooplus.integration.MailIntegration;
import com.client.api.rasmooplus.model.redis.UserRecoveryCode;
import com.client.api.rasmooplus.repositoy.redis.UserRecoveryCodeRepository;
import com.client.api.rasmooplus.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Value("${webservices.rasplus.redis.recoverycode.timeout}")
    private String recoveryCodeTimeout;


    @Autowired
    private UserRecoveryCodeRepository userRecoveryCodeRepository;

    @Autowired
    private MailIntegration mailIntegration;



    @Override
    public void sendRecoveryCode(String email) {

//        UserRecoveryCode userRecoveryCode;
//        String code = String.format("%04d", new Random().nextInt(10000));
//        var userRecoveryCodeOpt = userRecoveryCodeRepository.findByEmail(email);
//
//        if (userRecoveryCodeOpt.isEmpty()) {
//
//            var user = userDetailsRepository.findByUsername(email);
//            if (user.isEmpty()) {
//                throw new NotFoudException("Usuário não encontrado");
//            }
//
//            userRecoveryCode = new UserRecoveryCode();
//            userRecoveryCode.setEmail(email);
//
//        } else {
//            userRecoveryCode = userRecoveryCodeOpt.get();
//        }
//        userRecoveryCode.setCode(code);
//        userRecoveryCode.setCreationDate(LocalDateTime.now());
//
//        userRecoveryCodeRepository.save(userRecoveryCode);
//        mailIntegration.send(email, "Código de recuperação de conta: "+code, "Código de recuperação de conta");
    }

    @Override
    public boolean recoveryCodeIsValid(String recoveryCode, String email) {

        var userRecoveryCodeOpt = userRecoveryCodeRepository.findByEmail(email);

        if (userRecoveryCodeOpt.isEmpty()) {
            throw new NotFoudException("Usuário não encontrado");
        }

        UserRecoveryCode userRecoveryCode = userRecoveryCodeOpt.get();

        LocalDateTime timeout = userRecoveryCode.getCreationDate().plusMinutes(Long.parseLong(recoveryCodeTimeout));
        LocalDateTime now = LocalDateTime.now();

        return recoveryCode.equals(userRecoveryCode.getCode()) && now.isBefore(timeout);
    }

    @Override
    public void updatePasswordByRecoveryCode(UserDetailsDto userDetailsDto) {

        if (recoveryCodeIsValid(userDetailsDto.getRecoveryCode(), userDetailsDto.getEmail())) {
//            var userDetails = userDetailsRepository.findByUsername(userDetailsDto.getEmail());
//
//            UserCredentials userCredentials = userDetails.get();
//
//            userCredentials.setPassword(PasswordUtils.encode(userDetailsDto.getPassword()));
//
//            userDetailsRepository.save(userCredentials);
        }
    }
}
