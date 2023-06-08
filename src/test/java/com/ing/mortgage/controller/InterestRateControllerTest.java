package com.ing.mortgage.controller;

import com.ing.mortgage.service.InterestRateServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InterestRateController.class)
class InterestRateControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InterestRateServiceImpl interestRateService;


    @Test
    void testGetInterestRates_ValidRequest_ShouldReturnListOfInterestRates() throws Exception {
        // Given
        List<Double> interestRates = Arrays.asList(3.5, 4.0, 4.5);
        when(interestRateService.getAllInterestRates()).thenReturn(interestRates);

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/interest-rates").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$.length()").value(interestRates.size())).andExpect(jsonPath("$[0].value").value(interestRates.get(0))).andExpect(jsonPath("$[1].value").value(interestRates.get(1))).andExpect(jsonPath("$[2].value").value(interestRates.get(2)));

        // Then
        verify(interestRateService, times(1)).getAllInterestRates();
    }
}
