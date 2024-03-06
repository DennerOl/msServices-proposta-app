package com.mservices.propostaapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mservices.propostaapp.dto.PropostaRequestDTO;
import com.mservices.propostaapp.dto.PropostaResponseDTO;
import com.mservices.propostaapp.service.PropostaService;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

  private PropostaService propostaService;

  @PostMapping
  public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO requestDto) {
    PropostaResponseDTO response = propostaService.criar(requestDto);
    return ResponseEntity.ok(response);

  }
}
