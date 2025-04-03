package org.example.exercisespringallabout.infrastructure.adapter.out.persistence.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LicenseEntity {
    private String id;
    private Integer count;
    private String licenseKey;
    private String email;
    private boolean active;
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
}
