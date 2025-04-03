package org.example.exercisespringallabout.infrastructure.adapter.out.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exercisespringallabout.application.port.out.NotificationService;
import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.example.exercisespringallabout.infrastructure.config.NotificationProperties;
import org.springframework.stereotype.Component;

/**
 * 알림 서비스 어댑터
 * 애플리케이션 계층의 NotificationService 인터페이스를 구현
 * 실제 알림 전송은 내부적으로 직접 처리
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationServiceAdapter implements NotificationService, LicenseNotificationPort {
    private final NotificationProperties props;

    @Override
    public boolean notify(String email, String message) {
        try {
            log.debug("알림 전송 시작: {}", email);
            
            // 알림 타입에 따른 로깅
            String type = props.getNotificationType();
            if (type.startsWith("email")) {
                var emailProps = props.getEmail();
                log.info("📧 이메일 발신자: {}", emailProps.getSenderName());
                log.info("📧 이메일 발송: {} -> {}", emailProps.getSenderName(), email);
                log.info("🔁 재시도 횟수: {}", emailProps.getRetryCount());
            } else if (type.startsWith("sms")) {
                var smsProps = props.getSms();
                log.info("📱 SMS 발신 번호: {}", smsProps.getSenderNumber());
                log.info("📱 SMS 발송: {} -> {}", smsProps.getSenderNumber(), email);
                log.info("🔁 재시도 횟수: {}", smsProps.getRetryCount());
            }
            
            // 실제 알림 전송 시뮬레이션
            log.info("📤 메시지 전송: {}{}", email, message);
            
            log.info("알림 전송 성공: {}", email);
            return true;
        } catch (Exception e) {
            log.error("알림 전송 실패: {}", email, e);
            return false;
        }
    }
} 