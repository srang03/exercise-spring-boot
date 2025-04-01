package org.example.review_module.service;

import org.example.review_module.annotation.NameFilter;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {
    @NameFilter(name = "admin")
    public void createLicense(String license) {
        System.out.println("License created: " + license);
    }
}
