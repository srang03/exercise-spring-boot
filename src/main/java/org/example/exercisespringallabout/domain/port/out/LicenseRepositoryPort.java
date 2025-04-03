package org.example.exercisespringallabout.domain.port.out;


import org.example.exercisespringallabout.infrastructure.adapter.out.persistence.entity.LicenseEntity;

public interface LicenseRepositoryPort {
    void save(LicenseEntity license);
    LicenseEntity findById(String id);
    LicenseEntity deleteById(String id);
    LicenseEntity update(String id, LicenseEntity license);
}
