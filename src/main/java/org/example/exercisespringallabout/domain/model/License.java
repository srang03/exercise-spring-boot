package org.example.exercisespringallabout.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.exercisespringallabout.domain.vo.Email;

// 도메인 모델 (Domain Model & VO)
@AllArgsConstructor
@Getter
@Builder
public class License {
    private String id;
    private Integer count;
    private String licenseKey;
    private Email email;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    // 새로운 상태를 가진 새 객체를 반환하는 메서드 추가
    public License withLicenseKey(String licenseKey) {
        return new License(
                this.id,
                this.count,
                licenseKey,
                this.email,
                this.active
        );
    }

    public License withActive(boolean active) {
        return new License(
                this.id,
                this.count,
                this.licenseKey,
                this.email,
                active
        );
    }
}

