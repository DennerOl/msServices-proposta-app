package com.mservices.propostaapp.agendador;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mservices.propostaapp.entity.Proposta;
import com.mservices.propostaapp.repository.PropostaRepository;
import com.mservices.propostaapp.service.NotificacaoRabbitService;

@Component
public class PropostaSemIntegracao {

  private final PropostaRepository propostaRepository;

  private final NotificacaoRabbitService notificacaoRabbitService;

  private final String exchange;

  public PropostaSemIntegracao(PropostaRepository propostaRepository,
      NotificacaoRabbitService notificacaoRabbitService,
      @Value("${rabbitmq.propostapendente.exchange}") String exchange) {

    this.propostaRepository = propostaRepository;
    this.notificacaoRabbitService = notificacaoRabbitService;
    this.exchange = exchange;
  }

  private final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);

  @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
  public void buscarPropostaSemIntegracao() {
    propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {

      try {
        notificacaoRabbitService.notificar(proposta, exchange);
        atualizarProposta(proposta);

      } catch (RuntimeException ex) {
        logger.error(ex.getMessage());
      }

    });
  }

  private void atualizarProposta(Proposta proposta) {
    proposta.setIntegrada(true);
    propostaRepository.save(proposta);
  }
}
