package com.msservices.analisecredito.service.strategy.impl;

import java.util.Random;

import com.msservices.analisecredito.domain.Proposta;
import com.msservices.analisecredito.service.strategy.CalculoPonto;

public class OutrosEmprestimosEmAndamento implements CalculoPonto {

  @Override
  public int calcular(Proposta proposta) {

    return outroEmprestimosEmAndamento() ? 0 : 80;
  }

  private boolean outroEmprestimosEmAndamento() {
    return new Random().nextBoolean();
  }
}
