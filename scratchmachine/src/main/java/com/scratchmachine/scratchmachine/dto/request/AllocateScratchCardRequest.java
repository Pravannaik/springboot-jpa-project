package com.scratchmachine.scratchmachine.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllocateScratchCardRequest {

    @NotNull(message = "UserId should not be NULL")
    private Long userId;

    @Min(value = 1, message = "No of scratch card to allocate value should be greater than or equal to 1")
    private int numberOfScratchCardToAllocate;
}
