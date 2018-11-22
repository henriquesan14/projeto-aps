package br.com.henrique.service;

import br.com.henrique.dao.PacienteDao;
import br.com.henrique.domain.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteDao dao;

    @Override
    public void salvar(Paciente paciente){
        dao.save(paciente);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Paciente> buscar(){
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Paciente buscarPorId(Long id){
        return dao.findOne(id);
    }

    @Override
    public void editar(Paciente paciente) {
        dao.save(paciente);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Paciente> buscarPorNome(String nome) {
        return dao.findByName("%"+nome+"%");
    }


}
