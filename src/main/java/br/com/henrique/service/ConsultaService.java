package br.com.henrique.service;

import br.com.henrique.domain.Consulta;

import java.util.List;

public interface ConsultaService {

    void salvar(Consulta consulta);
    List<Consulta> buscar();
    Consulta buscarPorId(Long id);
    void editar(Consulta consulta);
    void excluir(Long id);
    Consulta buscarPorPaciente();
}
