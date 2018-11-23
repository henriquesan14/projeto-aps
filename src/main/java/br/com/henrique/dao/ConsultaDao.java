package br.com.henrique.dao;

import br.com.henrique.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaDao extends JpaRepository<Consulta, Long> {
}
