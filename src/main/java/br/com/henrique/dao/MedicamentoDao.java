package br.com.henrique.dao;

import br.com.henrique.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicamentoDao extends JpaRepository<Medicamento,Long> {

    @Query("from Medicamento m where m.nome like ?1")
    public List<Medicamento> findByName(String nome);

}
