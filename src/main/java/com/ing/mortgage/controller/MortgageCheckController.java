package com.ing.mortgage.controller;


import com.ing.mortgage.controller.dto.MortgageCheckRequestDTO;
import com.ing.mortgage.controller.dto.MortgageCheckResponseDTO;
import com.ing.mortgage.service.MortgageCheckServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mortgage-check")
@Tag(name = "Mortgage Check")
@RequiredArgsConstructor
public class MortgageCheckController {
    private final MortgageCheckServiceImpl mortgageCheckService;

    // I assume even if the mortgage is not feasible still ,the monthly costs should be returned
    @PostMapping
    @Operation(description = "Check if mortgage is feasible and calculate monthly costs")
    public ResponseEntity<MortgageCheckResponseDTO> performMortgageCheck(@RequestBody @Valid MortgageCheckRequestDTO requestDTO) {
        MortgageCheckResponseDTO response = mortgageCheckService.performMortgageCheck(requestDTO);
        return ResponseEntity.ok().body(response);
    }
}
