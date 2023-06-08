package com.ing.mortgage.configuration;

import com.ing.mortgage.domain.MortgageRate;
import com.ing.mortgage.repository.MortgageRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Log
@RequiredArgsConstructor
public class MortgageRateInitializer implements CommandLineRunner {

    private final MortgageRateRepository mortgageRateRepository;

    @Override
    public void run(String... args) {
        List<MortgageRate> initialRates = createInitialMortgageRates();
        mortgageRateRepository.saveAll(initialRates);
    }

    private List<MortgageRate> createInitialMortgageRates() {
        List<MortgageRate> rates = new ArrayList<>();
        rates.add(new MortgageRate(5, 3.5, LocalDateTime.now()));
        rates.add(new MortgageRate(10, 4.2, LocalDateTime.now()));
        rates.add(new MortgageRate(15, 4.8, LocalDateTime.now()));

        log.info("Initializing from Mortgage rates");

        return rates;
    }
}
