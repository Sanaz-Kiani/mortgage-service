package com.ing.mortgage.service;

import com.ing.mortgage.domain.MortgageRate;
import com.ing.mortgage.repository.MortgageRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InterestRateServiceTest {
    @InjectMocks
    private InterestRateServiceImpl interestRateService;

    @Mock
    private MortgageRateRepository mortgageRateRepository;


    @Test
    public void testGetAllInterestRates_ShouldReturnAllInterestRates() {
        // Given
        List<MortgageRate> mortgageRates = Arrays.asList(
                new MortgageRate(10, 3.5, LocalDateTime.now()),
                new MortgageRate(20, 4.0, LocalDateTime.now()),
                new MortgageRate(30, 4.5, LocalDateTime.now())
        );
        when(mortgageRateRepository.findAll()).thenReturn(mortgageRates);

        // When
        List<Double> interestRates = interestRateService.getAllInterestRates();

        // Then
        assertEquals(3, interestRates.size());
        assertEquals(3.5, interestRates.get(0));
        assertEquals(4.0, interestRates.get(1));
        assertEquals(4.5, interestRates.get(2));
    }
}

