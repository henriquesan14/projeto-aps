package br.com.henrique.service;

import br.com.henrique.dao.ConsultaDao;
import br.com.henrique.domain.Consulta;

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

    @Override
    public List<Consulta> consultasDoDiaPorMedico(LocalDate data, String nome) {
        return consultaDao.consultasDoDiaPorMedico(data,"%"+nome+"%");
    }

    @Transactional(readOnly = true)
    @Override
    public List<Consulta> consultasDoDiaAndamento(LocalDate data) {
        return consultaDao.consultasDoDiaAndamento(data);
    }

    @Override
    public List<Consulta> consultasDoDiaAndamentoPorMedico(LocalDate data, String nome) {
        return consultaDao.consultasDoDiaAndamentoPorMedico(data,"%"+nome+"%");
    }

    @Override
    public List<Consulta> consultasPorPaciente(String nome) {
        return consultaDao.consultasPorPaciente("%"+nome+"%");
    }

    @Override
    public List<Consulta> consultasPorMedico(String nome) {
        return consultaDao.consultasPorMedico("%"+nome+"%");
    }

    @Override
    public long verifica(Long id,LocalDate data,String turno) {
        return consultaDao.verifica(id,data,turno);
    }

    @Override
    public long consultasHoje(LocalDate data) {
        return consultaDao.consultasHoje(data);
    }

    @Override
    public long consultasAgendadasMes(Integer mes) {
        return consultaDao.consultasAgendadasMes(mes);
    }

    @Override
    public long consultasRealizadasMes(Integer mes) {
        return consultaDao.consultasRealizadasMes(mes);
    }


}
