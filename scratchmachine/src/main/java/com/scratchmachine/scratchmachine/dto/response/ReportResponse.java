package com.scratchmachine.scratchmachine.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportResponse {

    private Long userId;
    private BigDecimal totalWon = BigDecimal.ZERO;
    private BigDecimal totalUnused = BigDecimal.ZERO;
}
