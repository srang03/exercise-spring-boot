package org.example.exercisespringallabout.application.port.out;

/**
 * 애플리케이션 계층에서 정의하는 알림 서비스 포트 인터페이스
 * 이메일, SMS 등 알림 전송을 위한 추상화된 인터페이스
 * 실제 구현은 인프라스트럭처 계층에서 이루어짐
 */
public interface NotificationService {
    /**
     * 지정된 이메일로 메시지 전송
     * @param email 수신자 이메일
     * @param message 전송할 메시지
     * @return 전송 성공 여부
     */
    boolean notify(String email, String message);
} 