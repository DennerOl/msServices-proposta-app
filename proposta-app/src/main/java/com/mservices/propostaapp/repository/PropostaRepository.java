package com.mservices.propostaapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mservices.propostaapp.entity.Proposta;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

  // query derivada
  List<Proposta> findAllByIntegradaIsFalse();

}
