
package com.msservices.analisecredito.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.msservices.analisecredito.domain.Proposta;

@Component
public class PropostaPendenteListener {

  @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
  public void propostaPendente(Proposta proposta) {

  }

}
