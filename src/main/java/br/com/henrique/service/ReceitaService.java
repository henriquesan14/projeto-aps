package br.com.henrique.service;

import br.com.henrique.domain.Receita;

import java.util.List;

public interface ReceitaService {

    void salvar(Receita receita);
    List<Receita> buscar();
    Receita buscarPorId(Long id);
    void editar(Receita receita);
    void excluir(Long id);
    List<Receita> receitasPorConsulta(Long id);
}
