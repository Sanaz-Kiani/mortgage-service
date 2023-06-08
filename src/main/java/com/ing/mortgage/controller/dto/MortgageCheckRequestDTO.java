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
    @DecimalMin(value = "2000", inclusive = true, message = "Income must be a positive number and min 2000")
    private BigDecimal income;

    @Schema(description = "Maturity period in months", minimum = "1")
    @Min(value = 10, message = "Maturity period must be greater than 0")
    private int maturityPeriod;

    @Schema(description = "Loan value")
    @DecimalMin(value = "8000", inclusive = true, message = "Loan value must be a positive number and min 8000")
    private BigDecimal loanValue;

    @Schema(description = "Home value")
    @DecimalMin(value = "8000", inclusive = true, message = "Home value must be a positive number and min 8000 and min 8000")
    private BigDecimal homeValue;
}
