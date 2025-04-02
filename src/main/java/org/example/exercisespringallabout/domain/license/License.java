package org.example.exercisespringallabout.domain.license;

import lombok.RequiredArgsConstructor;
import org.example.exercisespringallabout.vo.Email;

// 도메인 모델 (Domain Model & VO)
@RequiredArgsConstructor
public class License {
    private final String id;
    private final Integer count;
    private final String licenseKey;
    private final Email email;
}
