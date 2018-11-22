package br.com.henrique.dao;

import br.com.henrique.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicoDao extends JpaRepository<Medico, Long> {

    @Query("from Medico m where m.nome like ?1")
    public List<Medico> findByName(String nome);
}
