package br.com.henrique.dao;

import br.com.henrique.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface ConsultaDao extends JpaRepository<Consulta, Long> {

    @Query("select c from Consulta c where c.dataConsulta=?1 and c.tipo in('agendada','retorno')")
    public List<Consulta> consultasDoDia(LocalDate data);

    @Query("select c from Consulta c where c.dataConsulta=?1 and c.medico.nome like ?2 and c.tipo in('agendada','retorno')")
    public List<Consulta> consultasDoDiaPorMedico(LocalDate data,String nome);

    @Query("select c from Consulta c where c.dataConsulta=?1 and c.tipo='andamento'")
    public List<Consulta> consultasDoDiaAndamento(LocalDate data);

    @Query("select c from Consulta c where c.dataConsulta=?1 and c.tipo='andamento' and c.medico.nome like ?2")
    public List<Consulta> consultasDoDiaAndamentoPorMedico(LocalDate data,String nome);

    @Query("from Consulta c where c.paciente.nome like ?1")
    public List<Consulta> consultasPorPaciente(String nome);

    @Query("from Consulta c where c.medico.nome like ?1")
    public List<Consulta> consultasPorMedico(String nome);

    @Query("select count(c) from Consulta c where c.medico.id=?1 and c.tipo='andamento'")
    public long verificaOcupado(Long id);

    @Query("select count(c) from Consulta c where c.medico.id=?1 and c.dataConsulta=?2 and c.turno=?3")
    public long verifica(Long id,LocalDate data,String turno);

    @Query("select count(c) from Consulta c where c.dataConsulta=?1")
    public long consultasHoje(LocalDate data);

    @Query("select count(c) from Consulta c where MONTH(c.dataConsulta) = ?1 and c.tipo='agendada' or c.tipo='retorno'")
    public long consultasAgendadasMes(Integer mes);

    @Query("select count(c) from Consulta c where MONTH(c.dataConsulta) = ?1 and c.tipo='finalizada' or c.tipo='retorno'")
    public long consultasRealizadasMes(Integer mes);


}
