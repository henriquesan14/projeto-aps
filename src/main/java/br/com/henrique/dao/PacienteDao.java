package br.com.henrique.dao;

import br.com.henrique.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteDao extends JpaRepository<Paciente, Long> {
}
