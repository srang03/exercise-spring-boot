package org.example.exercisespringallabout.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exercisespringallabout.application.port.out.LicenseRepository;
import org.example.exercisespringallabout.domain.model.License;
import org.example.exercisespringallabout.infrastructure.mapper.LicenseMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * LicenseRepository 인터페이스의 인메모리 구현체
 * 애플리케이션 계층의 포트 인터페이스를 직접 구현하여 중간 레이어 제거
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class LicenseRepositoryAdapter implements LicenseRepository {
    private final LicenseMapper licenseMapper;
    private final ConcurrentMap<String, License> licenses = new ConcurrentHashMap<>();
    
    @Override
    public void save(License license) {
        log.debug("라이센스 저장: {}", license.getId());
        licenses.put(license.getId(), license);
    }
    
    @Override
    public License findById(String id) {
        log.debug("라이센스 조회: {}", id);
        License license = licenses.get(id);
        
        if (license == null) {
            log.warn("라이센스를 찾을 수 없음: {}", id);
            return null;
        }
        
        return license;
    }
    
    @Override
    public License deleteById(String id) {
        log.debug("라이센스 삭제: {}", id);
        License removed = licenses.remove(id);
        
        if (removed == null) {
            log.warn("삭제할 라이센스를 찾을 수 없음: {}", id);
            return null;
        }
        
        log.info("라이센스 삭제 완료: {}", id);
        return removed;
    }
    
    @Override
    public License update(String id, License license) {
        log.debug("라이센스 업데이트: {}", id);
        
        if (!licenses.containsKey(id)) {
            log.warn("업데이트할 라이센스를 찾을 수 없음: {}", id);
            return null;
        }
        
        licenses.put(id, license);
        log.info("라이센스 업데이트 완료: {}", id);
        return license;
    }
} 