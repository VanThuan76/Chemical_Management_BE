package com.chemical.services;

import com.chemical.dto.request.ChemicalCreateRequestDTO;
import com.chemical.dto.request.ChemicalUpdateRequestDTO;
import com.chemical.dto.response.ChemicalResponseDTO;
import com.chemical.entity.Chemical;

import java.util.List;

public interface ChemicalService {
    List<ChemicalResponseDTO> getAllChemicals();

    Chemical findById(Long chemicalId);

    ChemicalResponseDTO findDetailsById(Long chemicalId);

    Chemical save(ChemicalCreateRequestDTO request);

    Chemical update(Long chemicalId, ChemicalUpdateRequestDTO request);

    void delete(Long chemicalId);
}
