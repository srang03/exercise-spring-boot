package org.example.exercisespringallabout.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exercisespringallabout.domain.vo.Email;

/**
 * 라이센스 도메인 모델
 * 불변(Immutable) 객체로 설계
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 프레임워크용
@Getter
@Builder
public class License {
    private String id;
    private Integer count;
    private String licenseKey;
    private Email email;
    private boolean active;
    
    /**
     * 새 라이센스 키로 새 License 객체 생성
     */
    public License withLicenseKey(String licenseKey) {
        return License.builder()
                .id(this.id)
                .count(this.count)
                .licenseKey(licenseKey)
                .email(this.email)
                .active(this.active)
                .build();
    }
    
    /**
     * 활성 상태 변경된 새 License 객체 생성
     */
    public License withActive(boolean active) {
        return License.builder()
                .id(this.id)
                .count(this.count)
                .licenseKey(this.licenseKey)
                .email(this.email)
                .active(active)
                .build();
    }
    
    /**
     * 활성 상태 확인
     */
    public boolean isActive() {
        return active;
    }
}

