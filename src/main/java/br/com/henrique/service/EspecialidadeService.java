package br.com.henrique.service;

import br.com.henrique.domain.Especialidade;

import java.util.List;

public interface EspecialidadeService {
    void salvar(Especialidade espec);
    List<Especialidade> buscar();
    Especialidade buscarPorId(Long id);
    void editar(Especialidade espec);
    void excluir(Long id);
    List<Especialidade> buscarPorNome(String nome);
}
