package com.mservices.propostaapp.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.mservices.propostaapp.entity.Proposta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NotificacaoService {

  private RabbitTemplate rabbitTemplate;

  public void notificar(Proposta proposta, String exchange) {
    rabbitTemplate.convertAndSend(exchange, "", proposta);
  }
}
