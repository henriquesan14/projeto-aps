package br.com.henrique.dao;

import br.com.henrique.domain.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceitaDao extends JpaRepository<Receita,Long> {

    @Query("select r from Receita r where r.consulta.id=?1")
    public List<Receita> receitasPorConsulta(Long id);
}
