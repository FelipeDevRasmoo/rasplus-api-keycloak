package com.client.api.rasmooplus.integration;

public interface MailIntegration {

    void send(String mailTo, String message, String subject);

}
