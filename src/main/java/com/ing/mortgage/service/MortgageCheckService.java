package com.ing.mortgage.service;

import com.ing.mortgage.controller.dto.MortgageCheckRequestDTO;
import com.ing.mortgage.controller.dto.MortgageCheckResponseDTO;

public interface MortgageCheckService {
    MortgageCheckResponseDTO performMortgageCheck(MortgageCheckRequestDTO request);


}
