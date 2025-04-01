package org.example.exercisespringallabout.config;

import org.example.exercisespringallabout.adapter.out.notification.EmailLicenseNotificationAdapter;
import org.example.exercisespringallabout.adapter.out.notification.SmsLicenseNotificationAdapter;
import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class LicenseNotificationConfigWithonditionalOnProperty {
    @Bean
    @ConditionalOnProperty(name = "license.notification-type", havingValue = "email")
    public LicenseNotificationPort emailAdapter() {
        return new EmailLicenseNotificationAdapter();
    }

    @Bean
    @ConditionalOnProperty(name = "license.notification-type", havingValue = "sms")
    public LicenseNotificationPort smsAdapter() {
        return new SmsLicenseNotificationAdapter();
    }
}
