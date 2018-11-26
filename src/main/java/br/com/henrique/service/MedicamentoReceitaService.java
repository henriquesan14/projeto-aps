package br.com.henrique.service;

import br.com.henrique.domain.MedicamentoPorReceita;

import java.util.List;

public interface MedicamentoReceitaService {

    void salvar(MedicamentoPorReceita medicamentoPorReceita);
    List<MedicamentoPorReceita> buscar();
    MedicamentoPorReceita buscarPorId(Long id);
    void editar(MedicamentoPorReceita medicamentoPorReceita);
    void excluir(Long id);
    List<MedicamentoPorReceita> medicamentosPorReceita(Long id);
    void apagaMedicamentosPorReceita(Long id);
}
