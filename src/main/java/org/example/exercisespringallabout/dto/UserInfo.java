package org.example.exercisespringallabout.dto;

import lombok.Getter;
import org.example.exercisespringallabout.annotation.MaskField;

@Getter
public class UserInfo {
    private String name;

    @MaskField
    private String phone;

    @MaskField
    private String email;

    public UserInfo(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

}
