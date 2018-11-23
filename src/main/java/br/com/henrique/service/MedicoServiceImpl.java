package br.com.henrique.service;

import br.com.henrique.dao.MedicoDao;
import br.com.henrique.domain.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    MedicoDao dao;

    @Override
    public void salvar(Medico medico) {
        dao.save(medico);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Medico> buscar() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Medico buscarPorId(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void editar(Medico medico) {
        dao.save(medico);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Medico> buscarPorNome(String nome) {
        return dao.findByName("%"+nome+"%");
    }
}
