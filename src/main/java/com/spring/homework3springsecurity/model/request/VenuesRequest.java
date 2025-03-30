package com.spring.homework3springsecurity.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenuesRequest {

    @NotBlank(message = "Event name cannot be blank")
    private String venueName;

    @NotBlank(message = "Event name cannot be blank")
    private String location;
}
