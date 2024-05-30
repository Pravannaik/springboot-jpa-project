package com.scratchmachine.scratchmachine.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    @NotBlank(message = "userId should not be NULL, please generate userId")
    private Long userId;

    @NotBlank(message = "First Name should not be empty or blank")
    private String firstName;

    @NotBlank(message = "Last Name should not be empty or blank")
    private String lastName;

    private boolean active = true;

}
