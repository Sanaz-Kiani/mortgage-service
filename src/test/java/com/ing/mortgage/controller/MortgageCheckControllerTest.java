package com.ing.mortgage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.mortgage.controller.dto.MortgageCheckRequestDTO;
import com.ing.mortgage.controller.dto.MortgageCheckResponseDTO;
import com.ing.mortgage.domain.MortgageRequest;
import com.ing.mortgage.service.MortgageCalculatorServiceImpl;
import com.ing.mortgage.service.MortgageFeasibilityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MortgageCheckController.class)
class MortgageCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MortgageFeasibilityServiceImpl mortgageFeasibilityService;

    @MockBean
    private MortgageCalculatorServiceImpl mortgageCalculatorService;


    @Test
    void PerformMortgageCheck_ValidRequest_ShoudReturnFesaiblityAndMonthlyCost() throws Exception {

        //Given
        MortgageCheckRequestDTO requestDTO = MortgageCheckRequestDTO.builder()
                .income(BigDecimal.valueOf(2000))
                .maturityPeriod(12)
                .loanValue(BigDecimal.valueOf(300000))
                .homeValue(BigDecimal.valueOf(300000))
                .build();

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        MortgageCheckResponseDTO responseDTO = MortgageCheckResponseDTO.builder()
                .monthlyCosts(BigDecimal.valueOf(1500))
                .feasible(true).build();

        when(mortgageFeasibilityService.validateMortgage(any(MortgageRequest.class))).thenReturn(true);
        when(mortgageCalculatorService.calculateMonthlyCost(any(MortgageRequest.class))).thenReturn(BigDecimal.valueOf(1500));

        //Having
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                //Then
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.feasible").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.monthlyCosts").value(1500));
    }

    @Test
    void testPerformMortgageCheck_InvalidInput_ShouldBeSetBadRequest() throws Exception {
        //Given
        MortgageCheckRequestDTO requestDTO = MortgageCheckRequestDTO.builder()
                .income(BigDecimal.valueOf(50))
                .maturityPeriod(12)
                .loanValue(BigDecimal.valueOf(300000))
                .homeValue(BigDecimal.valueOf(300000))
                .build();
        String requestBody = objectMapper.writeValueAsString(requestDTO);

        //Having
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mortgage-check").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                //Then
                .andExpect(status().isBadRequest());
    }
}
