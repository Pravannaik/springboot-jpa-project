package com.scratchmachine.scratchmachine.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    @NotNull(message = "UserId should not be NULL")
    private Long userId;

    @NotEmpty(message = "Item request should not be empty, must contain atleast 1 object")
    private List<ItemRequest> itemRequest;
}
