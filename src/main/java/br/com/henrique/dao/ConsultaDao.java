package br.com.henrique.dao;

import br.com.henrique.domain.Consulta;
import br.com.henrique.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaDao extends JpaRepository<Consulta, Long> {

    @Query("select c from Consulta c where c.dataConsulta=?1 and c.tipo='agendada' or c.tipo='retorno'")
    public List<Consulta> consultasDoDia(LocalDate data);

    @Query("select c from Consulta c where c.dataConsulta=?1 and c.tipo='andamento'")
    public List<Consulta> consultasDoDiaAndamento(LocalDate data);

}
