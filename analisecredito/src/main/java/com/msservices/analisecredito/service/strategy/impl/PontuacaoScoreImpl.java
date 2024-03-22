package com.msservices.analisecredito.service.strategy.impl;

import java.util.Random;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.msservices.analisecredito.constantes.MensagemConstante;
import com.msservices.analisecredito.domain.Proposta;
import com.msservices.analisecredito.exceptions.StrategyException;
import com.msservices.analisecredito.service.strategy.CalculoPonto;

@Component
public class PontuacaoScoreImpl implements CalculoPonto {

  @Order(2)
  @Override
  public int calcular(Proposta proposta) {

    int score = score();

    if (score < 200) {
      throw new StrategyException(String.format(MensagemConstante.PONTUACAO_SERASA_BAIXA,
          proposta.getUsuario().getNome()));

    } else if (score <= 400) {
      return 150;

    } else if (score <= 600) {
      return 180;

    } else {
      return 220;
    }
  }

  private int score() {
    return new Random().nextInt(0, 1000);
  }
}
