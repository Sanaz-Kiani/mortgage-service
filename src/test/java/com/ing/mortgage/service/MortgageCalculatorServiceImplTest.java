package com.ing.mortgage.service;

import com.ing.mortgage.domain.MortgageRequest;
import com.ing.mortgage.exception.NotValidInterestRateException;
import com.ing.mortgage.repository.MortgageRateRepository;
import com.ing.mortgage.util.MortgageCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MortgageCalculatorServiceImplTest {

    @Mock
    private MortgageRateRepository mortgageRateRepository;
    @Mock
    private MockedStatic<MortgageCalculator> mortgageCalculator;

    @Test
    void testCalculateMonthlyCost_ValidRequest_ReturnsMonthlyCost() {
        // Given
        double interestRate = 5;
        int maturityPeriod = 10;
        BigDecimal loanValue = BigDecimal.valueOf(8000);
        BigDecimal income = BigDecimal.valueOf(2000);
        BigDecimal homeValue = BigDecimal.valueOf(8000);
        MortgageRequest request = MortgageRequest.builder()
                .loanValue(loanValue)
                .income(income)
                .maturityPeriod(maturityPeriod)
                .homeValue(homeValue)
                .build();
        when(mortgageRateRepository.findInterestRateByMaturityPeriod(maturityPeriod))
                .thenReturn(Optional.of(interestRate));
        mortgageCalculator.when(() -> MortgageCalculator.calculateMonthlyCost(request.getLoanValue(), interestRate, request.getMaturityPeriod()))
                .thenReturn(BigDecimal.valueOf(40.0));

        // Having
        MortgageCalculatorServiceImpl calculatorService = new MortgageCalculatorServiceImpl(mortgageRateRepository);
        BigDecimal monthlyCost = calculatorService.calculateMonthlyCost(request);

        // Then
        assertEquals(BigDecimal.valueOf(40.0), monthlyCost);
    }

    @Test
    void testCalculateMonthlyCost_InvalidInterestRate_ThrowsNotValidInterestRateException() {
        // Given
        int maturityPeriod = 11;
        BigDecimal loanValue = BigDecimal.valueOf(8000);
        BigDecimal income = BigDecimal.valueOf(2000);
        MortgageRequest request = MortgageRequest.builder()
                .loanValue(loanValue)
                .income(income)
                .maturityPeriod(maturityPeriod)
                .build();
        //Having
        when(mortgageRateRepository.findInterestRateByMaturityPeriod(maturityPeriod))
                .thenReturn(Optional.empty());
        MortgageCalculatorServiceImpl calculatorService = new MortgageCalculatorServiceImpl(mortgageRateRepository);

        // Then
        assertThrows(NotValidInterestRateException.class, () -> calculatorService.calculateMonthlyCost(request));
    }
}
