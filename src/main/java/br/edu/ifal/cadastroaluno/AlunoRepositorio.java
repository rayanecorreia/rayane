package br.edu.ifal.cadastroaluno;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AlunoRepositorio extends CrudRepository<Aluno, Long>{
    List<Aluno> findByNomeContaining(String nome);

}