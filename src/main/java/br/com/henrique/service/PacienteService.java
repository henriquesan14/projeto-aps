package br.com.henrique.service;

import br.com.henrique.domain.Consulta;
import br.com.henrique.domain.Paciente;

import java.util.List;

public interface PacienteService {

    void salvar(Paciente paciente);
    List<Paciente> buscar();
    Paciente buscarPorId(Long id);
    void editar(Paciente paciente);
    void excluir(Long id);
    List<Paciente> buscarPorNome(String nome);
    List<Consulta> consultasPorPaciente(Long id);
}
