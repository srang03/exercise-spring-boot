package org.example.exercisespringallabout.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.exercisespringallabout.validator.ValidateId;

@Getter
@Builder
@AllArgsConstructor
public class LicenseRequest {
    @NotNull
    @ValidateId
    @NotBlank(message = "Id is not possible to be null or blank.")
    private String id;
    @Email(message = "The email format is incorrect.")
    private String email;
    @Min(value = 0, message = "The count must be greater than or equal to 0.")
    private Integer count;
}
