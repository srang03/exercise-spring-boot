package org.example.exercisespringallabout.config;

import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class LicenseNotificationConfig {

    @Value("${license.notification-type}")
    private String notificationBeanName;

    private final ApplicationContext context;

    public LicenseNotificationConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    @Primary
    public LicenseNotificationPort licenseNotificationService() {
        return (LicenseNotificationPort) context.getBean(notificationBeanName);
    }
}
