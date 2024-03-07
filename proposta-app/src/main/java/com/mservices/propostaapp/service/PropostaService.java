package com.mservices.propostaapp.service;

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
}
