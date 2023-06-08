package com.ing.mortgage.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Interest Rate DTO")
public class InterestRateDTO {

    @Schema(description = "Interest rate value")
    private Double value;

}