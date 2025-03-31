package org.example.exercisespringallabout.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "license.notification")
@Getter
@Setter
public class NotificationProperties {
    private String type;
    private String senderName;
    private int retryCount;
}
