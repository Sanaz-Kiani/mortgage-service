package com.ing.mortgage.repository;

import com.ing.mortgage.domain.MortgageRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MortgageRateRepositoryTest {

    private MortgageRateRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new MortgageRateInMemoryRepository();
    }
    @Test
    void saveAll_ShouldAddMortgageRatesToRepository() {
        // Given
        List<MortgageRate> mortgageRates = Arrays.asList(
                new MortgageRate(1, 10.0, LocalDateTime.now()),
                new MortgageRate(2, 15.0, LocalDateTime.now()),
                new MortgageRate(3, 20.0, LocalDateTime.now())
        );

        // When
        repository.saveAll(mortgageRates);
        List<MortgageRate> savedRates = repository.findAll();

        // Then
        assertEquals(mortgageRates, savedRates);
    }


    @Test
    void findInterestRateByMaturityPeriod_ExistingMaturityPeriod_ShouldReturnInterestRate() {
        // Given
        List<MortgageRate> mortgageRates = Arrays.asList(
                new MortgageRate(1, 10.0, LocalDateTime.now()),
                new MortgageRate(2, 15.0, LocalDateTime.now()),
                new MortgageRate(3, 20.0, LocalDateTime.now())
        );
        repository.saveAll(mortgageRates);

        // When
        Optional<Double> interestRate = repository.findInterestRateByMaturityPeriod(2);

        // Then
        assertTrue(interestRate.isPresent());
        assertEquals(15.0, interestRate.get());
    }

    @Test
    void findInterestRateByMaturityPeriod_NonexistentMaturityPeriod_ShouldReturnEmptyOptional() {
        // Given
        List<MortgageRate> mortgageRates = Arrays.asList(
                new MortgageRate(1, 10.0, LocalDateTime.now()),
                new MortgageRate(2, 15.0, LocalDateTime.now()),
                new MortgageRate(3, 20.0, LocalDateTime.now())
        );
        repository.saveAll(mortgageRates);

        // When
        Optional<Double> interestRate = repository.findInterestRateByMaturityPeriod(4);

        // Then
        assertFalse(interestRate.isPresent());
    }
}
