package org.example.exercisespringallabout.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "license")
@Getter
@Setter
public class NotificationProperties {
    private String notificationType; // "email" or "sms"
    private Email email;
    private Sms sms;

    @Setter
    @Getter
    public static class Email {
        private String senderName;
        private int retryCount;
    }

    @Getter
    @Setter
    public static class Sms {
        private String senderNumber;
        private int retryCount;
    }
}
