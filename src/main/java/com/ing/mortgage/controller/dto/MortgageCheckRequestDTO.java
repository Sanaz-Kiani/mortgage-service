package com.ing.mortgage.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@Schema(description = "Mortgage Check Request DTO")
public class MortgageCheckRequestDTO {
    @Schema(description = "Income amount", minimum = "2000")
    @DecimalMin(value = "2000", inclusive = true, message = "Income must be a positive number")
    private BigDecimal income;

    @Schema(description = "Maturity period in months", minimum = "1")
    @Min(value = 1, message = "Maturity period must be greater than 0")
    private int maturityPeriod;

    @Schema(description = "Loan value", minimum = "30000")
    @DecimalMin(value = "30000", inclusive = false, message = "Loan value must be a positive number")
    private BigDecimal loanValue;

    @Schema(description = "Home value", minimum = "30000")
    @DecimalMin(value = "30000", inclusive = false, message = "Home value must be a positive number")
    private BigDecimal homeValue;
}
