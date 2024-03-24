package com.mservices.propostaapp.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.mservices.propostaapp.dto.PropostaResponseDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class WebSocketService {

  private SimpMessagingTemplate template;

  public void notificar(PropostaResponseDTO proposta) {
    template.convertAndSend("/propostas", proposta);
  }

}
