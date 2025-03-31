package org.example.exercisespringallabout.config;

import org.example.exercisespringallabout.service.EmailLicenseNotificationService;
import org.example.exercisespringallabout.service.LicenseNotificationService;
import org.example.exercisespringallabout.service.SmsLicenseNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LicenseNotificationConfig {

    @Value("${license.notification-type}")
    private String notificationBeanName;

    private final ApplicationContext context;

    public LicenseNotificationConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public LicenseNotificationService licenseNotificationService() {
        return (LicenseNotificationService) context.getBean(notificationBeanName);
    }
}
