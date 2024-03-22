package com.msservices.analisecredito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.msservices.analisecredito.domain.Proposta;
import com.msservices.analisecredito.exceptions.StrategyException;
import com.msservices.analisecredito.service.strategy.CalculoPonto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AnaliseCreditoService {

  private List<CalculoPonto> calculoPontoList;

  private NotificacaoRabbitService notificacaoRabbitService;

  @Value("${rabbitmq.propostaconcluida.exchange}")
  private String exchangePropostaConcluida;

  public void analisar(Proposta proposta) {

    try {
      int pontos = calculoPontoList.stream()
          .mapToInt(impl -> impl.calcular(proposta)).sum();
      proposta.setAprovada(pontos > 350);

    } catch (StrategyException ex) {
      proposta.setAprovada(false);
      proposta.setObservacao(ex.getMessage());
    }

    notificacaoRabbitService.notificar(exchangePropostaConcluida, proposta);
  }

}
