package br.com.henrique.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min= 5, max= 50)
    @Column(nullable = false, length = 50)
    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "medicamento",cascade = CascadeType.ALL)
    private List<MedicamentoPorReceita> medicamentos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<MedicamentoPorReceita> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicamentoPorReceita> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
