package com.ing.mortgage.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@Schema(description = "Mortgage Check Response DTO")
public class MortgageCheckResponseDTO {

    @Schema(description = "Feasibility of the mortgage")
    private boolean feasible;

    @Schema(description = "Monthly costs of the mortgage")
    private BigDecimal monthlyCosts;
}
