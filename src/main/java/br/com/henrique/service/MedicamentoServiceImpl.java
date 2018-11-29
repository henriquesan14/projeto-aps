package br.com.henrique.service;

import br.com.henrique.dao.MedicamentoDao;
import br.com.henrique.domain.Medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    MedicamentoDao dao;

    @Override
    public void salvar(Medicamento medicamento) {
        dao.save(medicamento);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Medicamento> buscar() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Medicamento buscarPorId(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void editar(Medicamento medicamento) {
        dao.save(medicamento);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Medicamento> buscarPorNome(String nome) {
        return dao.findByName("%"+nome+"%");
    }
}
