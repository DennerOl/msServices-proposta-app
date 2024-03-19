package com.mservices.notificacao.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.mservices.notificacao.constante.MensagemConstante;
import com.mservices.notificacao.domain.Proposta;
import com.mservices.notificacao.service.NotificacaoSnsService;

@Component
public class PropostaPendenteListener {

  private NotificacaoSnsService notificacaoSnsService;

  @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
  public void propostaPendente(Proposta proposta) {

    String mensagem = String.format(MensagemConstante.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
    notificacaoSnsService.notificar(mensagem);
  }

}
