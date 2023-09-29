package br.julialellis.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import br.julialellis.entity.Pessoa;

public interface PessoaRepository extends JpaRepositoryImplementation<Pessoa, Long> {

}
