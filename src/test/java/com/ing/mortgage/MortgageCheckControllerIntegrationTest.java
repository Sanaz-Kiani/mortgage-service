package com.ing.mortgage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.mortgage.controller.dto.MortgageCheckRequestDTO;
import com.ing.mortgage.exception.NotValidInterestRateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class MortgageCheckControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPerformMortgageCheck_ValidRequest_ReturnFeasibilityAndMonthlyCost() throws Exception {
        MortgageCheckRequestDTO requestDTO = MortgageCheckRequestDTO.builder()
                .income(BigDecimal.valueOf(2000000))
                .maturityPeriod(10)
                .loanValue(BigDecimal.valueOf(200000))
                .homeValue(BigDecimal.valueOf(300000))
                .build();

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.feasible").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.monthlyCosts").value(840.0));
    }

    @Test
    void testPerformMortgageCheck_InvalidRequest_ThrowsNotValidInterestRateException() throws Exception {
        //Given
        MortgageCheckRequestDTO requestDTO = MortgageCheckRequestDTO.builder()
                .income(BigDecimal.valueOf(2000))
                .maturityPeriod(12)
                .loanValue(BigDecimal.valueOf(200000))
                .homeValue(BigDecimal.valueOf(300000))
                .build();

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        //Having
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                //Then
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotValidInterestRateException));
    }

    @Test
    void testPerformMortgageCheck_ValidRequest_NotFeasable() throws Exception {
        MortgageCheckRequestDTO requestDTO = MortgageCheckRequestDTO.builder()
                .income(BigDecimal.valueOf(3000))
                .maturityPeriod(10)
                .loanValue(BigDecimal.valueOf(200000))
                .homeValue(BigDecimal.valueOf(300000))
                .build();

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.feasible").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.monthlyCosts").value(840.0));
    }

    @Test
    void testGetInterestRates_ValidRequest_ReturnsAllInterestRates() throws Exception {
        // Perform GET request to "/api/v1/interest-rates"
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/interest-rates"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3));

    }
}
