package com.msservices.analisecredito.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.msservices.analisecredito.domain.Proposta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NotificacaoRabbitService {

  private RabbitTemplate rabbitTemplate;

  public void notificar(String exchange, Proposta proposta) {
    rabbitTemplate.convertAndSend(exchange, "", proposta);

  }
}
