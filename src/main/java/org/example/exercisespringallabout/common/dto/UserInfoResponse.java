package org.example.exercisespringallabout.common.dto;

import lombok.Getter;
import org.example.exercisespringallabout.common.annotation.MaskField;

@Getter
public class UserInfoResponse {
    private String name;

    @MaskField
    private String phone;

    @MaskField
    private String email;

    public UserInfoResponse(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

}
