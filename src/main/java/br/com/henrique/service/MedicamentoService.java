package br.com.henrique.service;

import br.com.henrique.domain.Medicamento;

import java.util.List;

public interface MedicamentoService {

    void salvar(Medicamento medicamento);
    List<Medicamento> buscar();
    Medicamento buscarPorId(Long id);
    void editar(Medicamento medicamento);
    void excluir(Long id);
    List<Medicamento> buscarPorNome(String nome);
}
