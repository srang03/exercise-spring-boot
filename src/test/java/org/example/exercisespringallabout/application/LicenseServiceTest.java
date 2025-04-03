package org.example.exercisespringallabout.application;

import org.example.exercisespringallabout.application.service.LicenseService;
import org.example.exercisespringallabout.common.dto.LicenseRequest;
import org.example.exercisespringallabout.common.dto.LicenseResponse;
import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.example.exercisespringallabout.infrastructure.config.NotificationProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.ErrorResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LicenseServiceTest {
    @Mock
    private LicenseNotificationPort licenseNotificationPort;

    @Mock
    private NotificationProperties notificationProperties;

    @InjectMocks
    private LicenseService licenseService;

    @Test
    void 라이선스_정상_발급_테스트() {
        // given
        LicenseRequest request =  LicenseRequest.builder()
                .id("user-1")
                .email("gildong@example.com")
                .count(3)
                .build();
        // 알림 타입을 email로 설정
        NotificationProperties.Email emailProps = mock(NotificationProperties.Email.class);
        when(notificationProperties.getNotificationType()).thenReturn("email");
        when(notificationProperties.getEmail()).thenReturn(emailProps);
        when(emailProps.getSenderName()).thenReturn("Admin");
        when(emailProps.getRetryCount()).thenReturn(3);

        // when
        LicenseResponse response = licenseService.issueLicense(request);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("user-1");
        assertThat(response.getLicenseKey()).isNotBlank();

    }

    @Test
    void 라이선스_발급_실패_케이스_이메일이_fail_포함() {
        // given
        LicenseRequest request = new LicenseRequest("user-2", "fail@example.com", 2);

        // then
        assertThatThrownBy(() -> licenseService.issueLicense(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 사용자입니다.");
    }

    @Test
    void 라이선스_발급_실패_케이스_null_요청() {
        // then
        assertThatThrownBy(() -> licenseService.issueLicense(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 사용자입니다.");
    }
}