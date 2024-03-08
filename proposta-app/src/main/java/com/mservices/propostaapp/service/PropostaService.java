package com.mservices.propostaapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mservices.propostaapp.dto.PropostaRequestDTO;
import com.mservices.propostaapp.dto.PropostaResponseDTO;
import com.mservices.propostaapp.entity.Proposta;
import com.mservices.propostaapp.mapper.PropostaMapper;
import com.mservices.propostaapp.repository.PropostaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PropostaService {

  private PropostaRepository propostaRepository;

  public PropostaResponseDTO criar(PropostaRequestDTO requestDto) {
    Proposta proposta = PropostaMapper.INSTANCE.converteDtoToProposta(requestDto);
    propostaRepository.save(proposta);
    return PropostaMapper.INSTANCE.convertEntityToDto(proposta);

  }

  public List<PropostaResponseDTO> obterProposta() {
    return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
  }
}
