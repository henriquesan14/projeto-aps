package br.com.henrique.service;

import br.com.henrique.dao.ReceitaDao;
import br.com.henrique.domain.Receita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReceitaServiceImpl implements ReceitaService {

    @Autowired
    ReceitaDao receitaDao;

    @Override
    public void salvar(Receita receita) {
        receitaDao.save(receita);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Receita> buscar() {
        return receitaDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Receita buscarPorId(Long id) {
        return receitaDao.findOne(id);
    }

    @Override
    public void editar(Receita receita) {
        receitaDao.save(receita);
    }

    @Override
    public void excluir(Long id) {
        receitaDao.delete(id);
    }

    @Override
    public List<Receita> receitasPorConsulta(Long id) {
        return receitaDao.receitasPorConsulta(id);
    }
}
