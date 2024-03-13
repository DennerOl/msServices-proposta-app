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

  private NotificacaoService notificacaoService;

  public PropostaResponseDTO criar(PropostaRequestDTO requestDto) {
    Proposta proposta = PropostaMapper.INSTANCE.converteDtoToProposta(requestDto);
    propostaRepository.save(proposta);

    PropostaResponseDTO response = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    notificacaoService.notificar(response, "proposta-pendente.ex");

    return response;

  }

  public List<PropostaResponseDTO> obterProposta() {
    return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
  }
}
