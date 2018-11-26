package br.com.henrique.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class MedicamentoPorReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min= 5, max= 30)
    @Column(nullable = false, length = 30)
    private String dosagem;

    @ManyToOne
    @JoinColumn(name="id_receita")
    private Receita receita;

    @ManyToOne
    @JoinColumn(name="id_medicamento")
    private Medicamento medicamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
}
