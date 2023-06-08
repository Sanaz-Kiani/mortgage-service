package com.ing.mortgage.service;

import com.ing.mortgage.controller.dto.MortgageCheckRequestDTO;
import com.ing.mortgage.controller.dto.MortgageCheckResponseDTO;
import com.ing.mortgage.repository.MortgageRateInMemoryRepository;
import com.ing.mortgage.util.MortgageCalculator;
import com.ing.mortgage.validator.specification.Specification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MortgageCheckServiceImplTest {
    @InjectMocks
    private MortgageCheckServiceImpl service;
    @Mock
    private MortgageRateInMemoryRepository mortgageRateRepository;
    @Mock
    private Specification<MortgageCheckRequestDTO> mortgageSpecification;
    @Mock
    private MockedStatic<MortgageCalculator> mortgageCalculator;

    @Test
    void performMortgageCheck_ValidRequest_ShouldReturnFeasibleResponse() {
        // Given
        MortgageCheckRequestDTO request = MortgageCheckRequestDTO.builder()
                .maturityPeriod(10)
                .loanValue(BigDecimal.valueOf(200000))
                .homeValue(BigDecimal.valueOf(300000))
                .income(BigDecimal.valueOf(6000))
                .build();

        BigDecimal expectedMonthlyCosts = BigDecimal.valueOf(1000);
        MortgageCheckResponseDTO expectedResponse = MortgageCheckResponseDTO.builder()
                .feasible(false)
                .monthlyCosts(expectedMonthlyCosts)
                .build();

        when(mortgageRateRepository.findInterestRateByMaturityPeriod(request.getMaturityPeriod()))
                .thenReturn(Optional.of(2.0));

        // Mocking the static method
        mortgageCalculator.when(() -> MortgageCalculator.calculateMonthlyCost(request.getLoanValue(), 2.0, request.getMaturityPeriod()))
                .thenReturn(expectedMonthlyCosts);

        // Having
        MortgageCheckResponseDTO response = service.performMortgageCheck(request);

        // Then
        assertEquals(expectedResponse, response);
        verify(mortgageRateRepository, times(1)).findInterestRateByMaturityPeriod(request.getMaturityPeriod());
    }
}