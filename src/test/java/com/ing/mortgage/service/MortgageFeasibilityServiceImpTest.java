package com.ing.mortgage.service;

import com.ing.mortgage.domain.MortgageRequest;
import com.ing.mortgage.util.MortgageCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MortgageFeasibilityServiceImpTest {
    @InjectMocks
    private MortgageFeasibilityServiceImpl service;

    @Mock
    private MockedStatic<MortgageCalculator> mortgageCalculator;

    @Test
    void validateMortgage_ValidRequest_ShouldReturnFeasibleResponse() {
        // Given
        MortgageRequest request = MortgageRequest.builder()
                .maturityPeriod(10)
                .loanValue(BigDecimal.valueOf(2000))
                .homeValue(BigDecimal.valueOf(8000))
                .income(BigDecimal.valueOf(8000))
                .build();
        // Mocking the static method
        mortgageCalculator.when(() -> MortgageCalculator.calculateMonthlyCost(request.getLoanValue(), 2.0, request.getMaturityPeriod()))
                .thenReturn(BigDecimal.valueOf(40.0));
        // Having
        boolean result = service.validateMortgage(request);

        // Then
        assertEquals(true, result);
    }
}