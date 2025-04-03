package org.example.exercisespringallabout.application.port.out;

import org.example.exercisespringallabout.domain.model.License;

/**
 * 라이센스 저장소 포트 인터페이스
 * 애플리케이션 계층에서 정의하고 인프라스트럭처 계층에서 구현
 * 도메인 모델을, 영속성 계층의 엔티티로 변환하는 책임은 구현체에 있음
 */
public interface LicenseRepository {
    /**
     * 라이센스 저장
     * @param license 저장할 라이센스 도메인 객체
     */
    void save(License license);
    
    /**
     * ID로 라이센스 조회
     * @param id 조회할 라이센스 ID
     * @return 조회된 라이센스, 없으면 null
     */
    License findById(String id);
    
    /**
     * ID로 라이센스 삭제
     * @param id 삭제할 라이센스 ID
     * @return 삭제된 라이센스, 없으면 null
     */
    License deleteById(String id);
    
    /**
     * 라이센스 업데이트
     * @param id 업데이트할 라이센스 ID
     * @param license 업데이트할 내용이 담긴 라이센스 객체
     * @return 업데이트된 라이센스, 없으면 null
     */
    License update(String id, License license);
} 