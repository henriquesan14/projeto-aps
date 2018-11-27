package br.com.henrique.service;

import br.com.henrique.domain.Consulta;

import java.time.LocalDate;

import java.util.List;

public interface ConsultaService {

    void salvar(Consulta consulta);
    List<Consulta> buscar();
    Consulta buscarPorId(Long id);
    void editar(Consulta consulta);
    void excluir(Long id);
    List<Consulta> consultasDoDia(LocalDate data);
    List<Consulta> consultasDoDiaPorMedico(LocalDate data,String nome);
    List<Consulta> consultasDoDiaAndamento(LocalDate data);
    List<Consulta> consultasDoDiaAndamentoPorMedico(LocalDate data,String nome);
    List<Consulta> consultasPorPaciente(String nome);
    List<Consulta> consultasPorMedico(String nome);
    long verifica(Long id,LocalDate data,String turno);
    long consultasHoje(LocalDate data);
    long consultasAgendadasMes(Integer mes);
    long consultasRealizadasMes(Integer mes);
    long verificaOcupado(Long id);
}
