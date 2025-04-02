package org.example.exercisespringallabout.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LicenseRequest {
    @NotNull
    @NotBlank(message = "ID는 공백일 수 없습니다.")
    private String id;
    @Email(message = "이메일 형식이 잘못되었습니다.")
    private String email;
    @Min(value = 0, message = "count는 0 이상이어야 합니다.")
    private Integer count;
}
