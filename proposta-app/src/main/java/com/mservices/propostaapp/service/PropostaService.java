package com.mservices.propostaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mservices.propostaapp.dto.PropostaRequestDTO;
import com.mservices.propostaapp.dto.PropostaResponseDTO;
import com.mservices.propostaapp.entity.Proposta;
import com.mservices.propostaapp.mapper.PropostaMapper;
import com.mservices.propostaapp.repository.PropostaRepository;

@Service
public class PropostaService {

  private PropostaRepository propostaRepository;

  private NotificacaoService notificacaoService;

  @Value("${rabbitmq.propostapendente.exchange}")
  private String exchange;

  public PropostaService(PropostaRepository propostaRepository,
      NotificacaoService notificacaoService,
      @Value("${rabbitmq.propostapendente.exchange}") String exchange) {

    this.propostaRepository = propostaRepository;
    this.notificacaoService = notificacaoService;
    this.exchange = exchange;
  }

  public PropostaResponseDTO criar(PropostaRequestDTO requestDto) {
    Proposta proposta = PropostaMapper.INSTANCE.converteDtoToProposta(requestDto);
    propostaRepository.save(proposta);

    PropostaResponseDTO response = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    notificacaoService.notificar(response, exchange);

    return response;

  }

  public List<PropostaResponseDTO> obterProposta() {
    return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
  }
}
