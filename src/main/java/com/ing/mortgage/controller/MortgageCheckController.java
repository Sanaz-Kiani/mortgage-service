package com.ing.mortgage.controller;


import com.ing.mortgage.controller.dto.MortgageCheckRequestDTO;
import com.ing.mortgage.controller.dto.MortgageCheckResponseDTO;
import com.ing.mortgage.domain.MortgageRequest;
import com.ing.mortgage.service.MortgageFeasibilityService;
import com.ing.mortgage.service.MortgageCalculatorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/mortgage-check")
@Tag(name = "Mortgage Check")
@RequiredArgsConstructor
public class MortgageCheckController {
    private final MortgageFeasibilityService mortgageCheckService;
    private final MortgageCalculatorServiceImpl mortgageCalculatorService;

    // I assume even if the mortgage is not feasible still ,the monthly costs should be returned
    @PostMapping
    @Operation(description="Check if mortgage is feasible and calculate monthly costs")
    public ResponseEntity<MortgageCheckResponseDTO> performMortgageCheck(@RequestBody @Valid MortgageCheckRequestDTO requestDTO) {
        MortgageRequest mortgageRequest = createMortgageRequestFromDTO(requestDTO);
        boolean feasible = mortgageCheckService.validateMortgage(mortgageRequest);
        BigDecimal monthlyCost = mortgageCalculatorService.calculateMonthlyCost(mortgageRequest);
        MortgageCheckResponseDTO response = createMortgageCheckResponse(feasible, monthlyCost);
        return ResponseEntity.ok().body(response);
    }

    private MortgageRequest createMortgageRequestFromDTO(MortgageCheckRequestDTO requestDTO) {
        return MortgageRequest.builder()
                .homeValue(requestDTO.getHomeValue())
                .income(requestDTO.getIncome())
                .loanValue(requestDTO.getLoanValue())
                .maturityPeriod(requestDTO.getMaturityPeriod())
                .build();
    }
    private MortgageCheckResponseDTO createMortgageCheckResponse(boolean feasible, BigDecimal monthlyCost) {
        return MortgageCheckResponseDTO.builder()
                .feasible(feasible)
                .monthlyCosts(monthlyCost)
                .build();
    }
}
