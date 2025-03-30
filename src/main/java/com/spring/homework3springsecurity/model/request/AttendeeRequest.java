package com.spring.homework3springsecurity.model.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {

    @NotBlank(message = "Event name cannot be blank")
    private String attendeeName;
    @Email(message = "Invalid email format")
    private String email;
}
