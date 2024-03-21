package com.msservices.analisecredito.service.strategy.impl;

import java.util.Random;

import com.msservices.analisecredito.domain.Proposta;
import com.msservices.analisecredito.service.strategy.CalculoPonto;

public class NomeNegativadoImpl implements CalculoPonto {

  @Override
  public int calcular(Proposta proposta) {

    if (nomeNegativado()) {
      throw new RuntimeException("Nome negativado");
    }
    return 100;
  }

  private boolean nomeNegativado() {
    return new Random().nextBoolean();
  }
}
