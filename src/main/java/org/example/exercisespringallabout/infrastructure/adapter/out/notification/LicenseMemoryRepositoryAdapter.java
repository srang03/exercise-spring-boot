package org.example.exercisespringallabout.infrastructure.adapter.out.notification;

import org.example.exercisespringallabout.domain.port.out.LicenseRepositoryPort;
import org.example.exercisespringallabout.infrastructure.adapter.out.persistence.entity.LicenseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LicenseMemoryRepositoryAdapter implements LicenseRepositoryPort {
    private final List<LicenseEntity> licenseEntities = new ArrayList<>();


    @Override
    public void save(LicenseEntity license) {
        licenseEntities.add(license);
    }

    @Override
    public LicenseEntity findById(String id) {
        LicenseEntity licenseEntity = licenseEntities.stream().filter(findLicense -> findLicense.getId().equals(id)).findAny().orElse(null);
        if(licenseEntity == null) {
            throw new IllegalArgumentException("License not found");
        }
        return licenseEntity;
    }

    @Override
    public LicenseEntity deleteById(String id) {
        LicenseEntity licenseEntity = licenseEntities.stream().filter(findLicense -> findLicense.getId().equals(id)).findAny().orElse(null);
        if(licenseEntity != null) {
            licenseEntities.remove(licenseEntity);
            return licenseEntity;
        }
        throw new IllegalArgumentException("License not found");
    }

    @Override
    public LicenseEntity update(String id, LicenseEntity updatedLicense) {
        int index = licenseEntities.indexOf(licenseEntities.stream().filter(findLicense -> findLicense.getId().equals(id)).findAny().orElse(null));
        if (index != -1) {
            licenseEntities.set(index, updatedLicense);
            return updatedLicense;
        }
        throw new IllegalArgumentException("License not found");
    }
}
