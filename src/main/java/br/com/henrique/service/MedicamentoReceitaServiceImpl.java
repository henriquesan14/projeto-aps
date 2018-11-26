package br.com.henrique.service;

import br.com.henrique.dao.MedicamentoPorReceitaDao;
import br.com.henrique.domain.MedicamentoPorReceita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicamentoReceitaServiceImpl  implements MedicamentoReceitaService{

    @Autowired
    MedicamentoPorReceitaDao dao;


    @Override
    public void salvar(MedicamentoPorReceita medicamentoPorReceita) {
        dao.save(medicamentoPorReceita);
    }

    @Override
    public List<MedicamentoPorReceita> buscar() {
        return dao.findAll();
    }

    @Override
    public MedicamentoPorReceita buscarPorId(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void editar(MedicamentoPorReceita medicamentoPorReceita) {
        dao.save(medicamentoPorReceita);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public List<MedicamentoPorReceita> medicamentosPorReceita(Long id) {
        return dao.medicamentosPorReceita(id);
    }

    @Override
    public void apagaMedicamentosPorReceita(Long id) {
        dao.apagaMedicamentosPorReceita(id);
    }
}
