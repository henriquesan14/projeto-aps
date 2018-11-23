package br.com.henrique.domain;

public enum StatusConsulta {
    AGENDADA("agendada"),ANDAMENTO("andamento"),FINALIZADA("finalizada"),RETORNO("retorno");

    private String status;

    StatusConsulta(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
