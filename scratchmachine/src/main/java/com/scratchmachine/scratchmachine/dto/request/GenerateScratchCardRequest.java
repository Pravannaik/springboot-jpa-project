package com.scratchmachine.scratchmachine.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenerateScratchCardRequest {

    @NotNull(message = "No of scratch card should not be NULL")
    @Min(value = 1, message = "No of scratch card value should be greater than or equal to 1")
    private Long numberOfScratchCard;

    @NotNull(message = "Discount should not be NULL")
    @Min(value = 1, message = "Discount value should be greater than or equal to 1")
    private BigDecimal discount;

    @Min(value = 1, message = "No of days to expire value should be greater than or equal to 1")
    private int daysToExpire;

    private boolean deactivateOldScratchCard;
}
