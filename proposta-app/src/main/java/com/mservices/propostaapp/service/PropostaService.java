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

  private NotificacaoRabbitService notificacaoRabbitService;

  @Value("${rabbitmq.propostapendente.exchange}")
  private String exchange;

  public PropostaService(PropostaRepository propostaRepository,
      NotificacaoRabbitService NotificacaoRabbitService,
      @Value("${rabbitmq.propostapendente.exchange}") String exchange) {

    this.propostaRepository = propostaRepository;
    this.notificacaoRabbitService = NotificacaoRabbitService;
    this.exchange = exchange;
  }

  public PropostaResponseDTO criar(PropostaRequestDTO requestDto) {
    Proposta proposta = PropostaMapper.INSTANCE.converteDtoToProposta(requestDto);
    propostaRepository.save(proposta);

    notificarRabbitMQ(proposta);

    return PropostaMapper.INSTANCE.convertEntityToDto(proposta);

  }

  public List<PropostaResponseDTO> obterProposta() {
    return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
  }

  /*
   * metodo para notificar quando o rabbit estiver fora do ar
   * ele pega a proposta e set para falsa e depois vou fazer um
   * metodo para repassar essas proposta falsas no rabbitMQ
   */
  private void notificarRabbitMQ(Proposta proposta) {
    try {
      notificacaoRabbitService.notificar(proposta, exchange);

    } catch (RuntimeException ex) {
      proposta.setIntegrada(false);
      propostaRepository.save(proposta);

    }
  }
}
