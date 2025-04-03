package org.example.exercisespringallabout.adapter.out.notification;

import org.example.exercisespringallabout.domain.License;
import org.example.exercisespringallabout.domain.port.out.LicenseRepositoryPort;
import org.example.exercisespringallabout.entity.LicenseEntity;

import java.util.ArrayList;
import java.util.List;

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
        }
        throw new IllegalArgumentException("License not found");
    }

    @Override
    public LicenseEntity update(String id, LicenseEntity license) {
        int index = licenseEntities.indexOf(licenseEntities.stream().filter(findLicense -> findLicense.getId().equals(id)).findAny().orElse(null));
        if (index != -1) {
            licenseEntities.set(index, license);
            return license;
        }
        throw new IllegalArgumentException("License not found");
    }
}
