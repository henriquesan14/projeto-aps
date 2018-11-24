package br.com.henrique.dao;

import br.com.henrique.domain.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EspecialidadeDao extends JpaRepository<Especialidade,Long> {

    @Query("from Especialidade e where e.nome like ?1")
    public List<Especialidade> findByName(String nome);
}
