package br.com.henrique.dao;

import br.com.henrique.domain.MedicamentoPorReceita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoPorReceitaDao extends JpaRepository<MedicamentoPorReceita,Long> {
}
