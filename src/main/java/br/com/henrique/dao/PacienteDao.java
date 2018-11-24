package br.com.henrique.dao;

import br.com.henrique.domain.Consulta;
import br.com.henrique.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.servlet.Registration;
import java.util.List;

public interface PacienteDao extends JpaRepository<Paciente, Long> {

    @Query("from Paciente p where p.nome like ?1")
    public List<Paciente> findByName(String nome);

    @Query("from Consulta c where c.paciente.id=?1")
    public List<Consulta> consultasPorPaciente(Long id);
}
