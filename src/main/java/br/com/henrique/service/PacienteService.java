package br.com.henrique.service;

import br.com.henrique.dao.PacienteDao;
import br.com.henrique.domain.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PacienteService {

    @Autowired
    PacienteDao dao;

    public void salvar(Paciente paciente){
        dao.save(paciente);
    }

    public List<Paciente> buscar(){
        return dao.findAll();
    }

    public Paciente buscarPorId(Long id){
        return dao.findOne(id);
    }
}
