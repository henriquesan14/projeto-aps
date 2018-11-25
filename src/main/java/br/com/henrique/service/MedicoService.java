package br.com.henrique.service;

import br.com.henrique.domain.Consulta;
import br.com.henrique.domain.Medico;

import java.util.List;

public interface MedicoService {

    void salvar(Medico medico);
    List<Medico> buscar();
    Medico buscarPorId(Long id);
    void editar(Medico medico);
    void excluir(Long id);
    List<Medico> buscarPorNome(String nome);
    List<Medico> buscarPorEspec(String nome);
    List<Consulta> consultasPorMedico(Long id);

}
