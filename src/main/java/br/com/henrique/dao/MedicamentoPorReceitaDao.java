package br.com.henrique.dao;

import br.com.henrique.domain.MedicamentoPorReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MedicamentoPorReceitaDao extends JpaRepository<MedicamentoPorReceita,Long> {

    @Query("select m from MedicamentoPorReceita m where m.receita.id=?1 ")
    public List<MedicamentoPorReceita> medicamentosPorReceita(Long id);

    @Transactional
    @Modifying
    @Query("delete from MedicamentoPorReceita m where m.receita.id=?1")
    public void apagaMedicamentosPorReceita(Long id);
}
