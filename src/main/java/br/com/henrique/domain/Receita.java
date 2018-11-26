package br.com.henrique.domain;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;

    @Column(name = "data_receita", columnDefinition = "DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataReceita;


    @OneToMany(mappedBy = "receita",cascade = CascadeType.ALL)
    private List<MedicamentoPorReceita> medicamentos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public LocalDate getDataReceita() {
        return dataReceita;
    }

    public void setDataReceita(LocalDate dataReceita) {
        this.dataReceita = dataReceita;
    }

    public List<MedicamentoPorReceita> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicamentoPorReceita> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void addMedicamento(MedicamentoPorReceita medicamentoPorReceita){
        if(medicamentos==null){
            medicamentos = new ArrayList<>();
        }
        medicamentoPorReceita.setReceita(this);
        this.medicamentos.add(medicamentoPorReceita);
    }
}
