package br.com.henrique.service;

import br.com.henrique.dao.EspecialidadeDao;
import br.com.henrique.domain.Especialidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EspecialidadeServiceImpl implements EspecialidadeService {

    @Autowired
    EspecialidadeDao dao;

    @Override
    public void salvar(Especialidade espec) {
        dao.save(espec);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Especialidade> buscar() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Especialidade buscarPorId(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void editar(Especialidade espec) {
        dao.save(espec);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public List<Especialidade> buscarPorNome(String nome) {
        return dao.findByName("%"+nome+"%");
    }
}
