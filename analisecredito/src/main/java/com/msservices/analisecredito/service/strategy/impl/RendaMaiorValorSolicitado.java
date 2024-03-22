package com.msservices.analisecredito.service.strategy.impl;

import org.springframework.stereotype.Component;

import com.msservices.analisecredito.domain.Proposta;
import com.msservices.analisecredito.service.strategy.CalculoPonto;

@Component
public class RendaMaiorValorSolicitado implements CalculoPonto {

  @Override
  public int calcular(Proposta proposta) {
    return rendaMaiorValorSolicitado(proposta) ? 100 : 0;
  }

  private boolean rendaMaiorValorSolicitado(Proposta proposta) {
    return proposta.getUsuario().getRenda() > proposta.getValorSolicitado();

  }

}
