package com.client.api.rasmooplus.controller;


import com.client.api.rasmooplus.dto.PaymentProcessDto;
import com.client.api.rasmooplus.service.PaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentInfoController {

    @Autowired
    private PaymentInfoService paymentInfoService;

    @PostMapping("/process")
    @PreAuthorize(value = "hasAnyAuthority('CLIENT_READ_WRITE','ADMIN_READ','ADMIN_WRITE')")
    public ResponseEntity<Boolean> process(@RequestBody PaymentProcessDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(paymentInfoService.process(dto));
    }

}
