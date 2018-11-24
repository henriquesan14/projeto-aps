package br.com.henrique.service;

import br.com.henrique.dao.ConsultaDao;
import br.com.henrique.domain.Consulta;
import br.com.henrique.domain.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    ConsultaDao consultaDao;

    @Override
    public void salvar(Consulta consulta) {
        consultaDao.save(consulta);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Consulta> buscar() {
        return consultaDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Consulta buscarPorId(Long id) {
        return consultaDao.findOne(id);
    }

    @Override
    public void editar(Consulta consulta) {
        consultaDao.save(consulta);
    }

    @Override
    public void excluir(Long id) {
        consultaDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Consulta> consultasDoDia(LocalDate data) {
        return consultaDao.consultasDoDia(data);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Consulta> consultasDoDiaAndamento(LocalDate data) {
        return consultaDao.consultasDoDiaAndamento(data);
    }


}
