package br.com.henrique.domain;

import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String tipo;

    @NotNull
    private String turno;

    @Column(name = "data_consulta", columnDefinition = "DATE")
    @NotNull(message = "Informe a data da consulta.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataConsulta;

    @Column(name = "data_retorno", columnDefinition = "DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataRetorno;

    @ManyToOne
    @JoinColumn(name="id_paciente")
    @NotNull(message = "Informe o paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="id_medico")
    @NotNull(message = "Informe o médico")
    private Medico medico;


    private LocalTime hora;

    @OneToMany(mappedBy = "consulta",cascade = CascadeType.ALL)
    private List<Receita> receitas;


    private String diagnostico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalDate getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(LocalDate dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public boolean verificaAtender(){
        LocalDate date=LocalDate.now();
        boolean data=false;
        boolean tipo=false;
        boolean ok=false;
        if(this.dataConsulta.equals(date) || this.dataRetorno.equals(date)){
            data = true;
        }
        if(this.tipo.equals("agendada") || this.tipo.equals("retorno")){
            tipo=true;
        }
        if(data && tipo){
            return true;
        }
        return false;
    }
}
