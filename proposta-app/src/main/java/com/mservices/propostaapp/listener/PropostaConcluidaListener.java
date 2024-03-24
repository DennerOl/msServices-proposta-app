package com.mservices.propostaapp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.mservices.propostaapp.dto.PropostaResponseDTO;
import com.mservices.propostaapp.entity.Proposta;
import com.mservices.propostaapp.mapper.PropostaMapper;
import com.mservices.propostaapp.repository.PropostaRepository;
import com.mservices.propostaapp.service.WebSocketService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PropostaConcluidaListener {

  private PropostaRepository propostaRepository;
  private WebSocketService webSocketService;

  @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
  public void propostaConcluida(Proposta proposta) {
    atualizarProposta(proposta);
    PropostaResponseDTO responseDTO = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    webSocketService.notificar(responseDTO);
  }

  private void atualizarProposta(Proposta proposta) {
    propostaRepository.atualizarProposta(proposta.getId(), proposta.getAprovada(), proposta.getObservacao());
  }
}
