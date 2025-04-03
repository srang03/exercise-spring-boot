package org.example.exercisespringallabout.domain.port.out;

/**
 * 라이센스 알림 전송을 위한 인터페이스
 * 실제 구현은 이메일 또는 SMS 등 다양한 방식으로 가능
 */
public interface LicenseNotificationPort {
    /**
     * 지정된 이메일로 메시지 전송
     * @param email 수신자 이메일
     * @param message 전송할 메시지
     * @return 전송 성공 여부
     */
    boolean notify(String email, String message);
}
