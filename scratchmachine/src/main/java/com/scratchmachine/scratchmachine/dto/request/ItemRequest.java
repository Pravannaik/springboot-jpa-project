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
public class ItemRequest {

    @NotNull(message = "item id should not be NULL")
    private Long itemId;

    @NotNull(message = "Quantity should not be NULL")
    @Min(value = 1, message = "Quantity value should be greater than or equal to 1")
    private Long quantity;

    @NotNull(message = "Scratch card id should not be NULL")
    private Long scratchCardId;
}
