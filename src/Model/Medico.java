package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Medico {

    private int idMedico;
    private String crm;
    private Pessoa pessoa;
    private String especialidade;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Medico() {
    }
    
    public Medico(String crm, Pessoa pessoa, String especialidade,
            LocalDateTime dataCriacao) {
        this.crm = crm;
        this.pessoa = pessoa;
        this.especialidade = especialidade;
        this.dataCriacao = dataCriacao;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    
    public int getIdMedico() {
        return idMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idMedico;
        hash = 89 * hash + Objects.hashCode(this.crm);
        hash = 89 * hash + Objects.hashCode(this.pessoa);
        hash = 89 * hash + Objects.hashCode(this.especialidade);
        hash = 89 * hash + Objects.hashCode(this.dataCriacao);
        hash = 89 * hash + Objects.hashCode(this.dataModificacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medico other = (Medico) obj;
        if (this.idMedico != other.idMedico) {
            return false;
        }
        if (!Objects.equals(this.crm, other.crm)) {
            return false;
        }
        if (!Objects.equals(this.especialidade, other.especialidade)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        if (!Objects.equals(this.dataModificacao, other.dataModificacao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        DateTimeFormatter fd = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        if (dataModificacao == null) {
            return "Medico(a): " + this.getPessoa().getNomePessoa() + "," + " "
                    + "Telefone: " + this.getPessoa().getTelefonePessoa() + "," + " "
                    + "ID - Medico: " + this.idMedico + "," + " "
                    + "Crm: " + this.crm + "," + " "  + "\n"
                    + "Especialidade: " + this.especialidade + "," + " "
                    + "Data e Hora de Criacao: " + fd.format(this.dataCriacao) + "," + " ";
        } else {
            return "Medico(a): " + this.getPessoa().getNomePessoa() + "," + " "
                    + "Telefone: " + this.getPessoa().getTelefonePessoa() + "," + " " 
                    + "ID - Medico: " + this.idMedico + "," + " "
                    + "Crm: " + this.crm + "," + " " + "\n"
                    + "Especialidade: " + this.especialidade + "," + " "
                    + "Data e Hora de Criacao: " + fd.format(this.dataCriacao) + "," + " "
                    + "Data e Hora de Modificacao: " + fd.format(this.dataModificacao) + "," + " ";
        }

    }

}
