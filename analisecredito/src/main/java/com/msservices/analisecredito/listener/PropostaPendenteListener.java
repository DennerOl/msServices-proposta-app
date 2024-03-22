
package com.msservices.analisecredito.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.msservices.analisecredito.domain.Proposta;
import com.msservices.analisecredito.service.AnaliseCreditoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PropostaPendenteListener {

  private final AnaliseCreditoService analiseCreditoService;

  @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
  public void propostaPendente(Proposta proposta) {

  }

}
