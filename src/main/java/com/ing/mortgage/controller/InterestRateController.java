package com.ing.mortgage.controller;

import com.ing.mortgage.controller.dto.InterestRateDTO;
import com.ing.mortgage.service.InterestRateServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interest-rates")
@Tag(name = "Interest Rates")
@Log
@RequiredArgsConstructor
public class InterestRateController {

    private final InterestRateServiceImpl interestRateService;

    @Operation(description = "Get all interest rates")
    @GetMapping
    public ResponseEntity<List<InterestRateDTO>> getInterestRates() {
        List<Double> interestRates = interestRateService.getAllInterestRates();
        List<InterestRateDTO> interestRateDTOs = interestRates.stream()
                .map(InterestRateDTO::new)
                .toList();
        return new ResponseEntity<>(interestRateDTOs, HttpStatus.OK);
    }
}

