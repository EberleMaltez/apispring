package com.example.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pessoa.entity.Pessoa;

public interface IPessoaRepository extends JpaRepository<Pessoa, Long> {

}
