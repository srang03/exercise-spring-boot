package org.example.exercisespringallabout.domain.port.out;

import org.example.exercisespringallabout.entity.LicenseEntity;

public interface LicenseRepositoryPort {
    void save(LicenseEntity license);
    LicenseEntity findById(String id);
    LicenseEntity deleteById(String id);
    LicenseEntity update(String id, LicenseEntity license);
}
