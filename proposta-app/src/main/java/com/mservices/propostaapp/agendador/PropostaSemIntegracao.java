package com.mservices.propostaapp.agendador;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mservices.propostaapp.repository.PropostaRepository;
import com.mservices.propostaapp.service.NotificacaoRabbitService;

@Component
public class PropostaSemIntegracao {

  private PropostaRepository propostaRepository;

  private NotificacaoRabbitService notificacaoRabbitService;

  private String exchange;

  public PropostaSemIntegracao(PropostaRepository propostaRepository,
      NotificacaoRabbitService notificacaoRabbitService,
      @Value("${rabbitmq.propostapendente.exchange}") String exchange) {

    this.propostaRepository = propostaRepository;
    this.notificacaoRabbitService = notificacaoRabbitService;
    this.exchange = exchange;
  }

  public void buscarPropostaSemIntegracao() {

    propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {

      try {
        notificacaoRabbitService.notificar(proposta, exchange);
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);

      } catch (RuntimeException ex) {
        System.out.println(ex);
      }

    });
  }
}
