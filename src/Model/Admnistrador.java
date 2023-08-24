package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Admnistrador {

    private int idAdmnistrador;
    private Pessoa pessoa;
    private Franquia franquia;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Admnistrador() {
    }

    public void setIdAdmnistrador(int idAdmnistrador) {
        this.idAdmnistrador = idAdmnistrador;
    }
    

    public int getIdAdmnistrador() {
        return idAdmnistrador;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Franquia getFranquia() {
        return franquia;
    }

    public void setFranquia(Franquia franquia) {
        this.franquia = franquia;
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

    public Admnistrador(Pessoa pessoa, Franquia franquia, LocalDateTime dataCriacao) {
        this.pessoa = pessoa;
        this.franquia = franquia;
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.idAdmnistrador;
        hash = 37 * hash + Objects.hashCode(this.pessoa);
        hash = 37 * hash + Objects.hashCode(this.franquia);
        hash = 37 * hash + Objects.hashCode(this.dataCriacao);
        hash = 37 * hash + Objects.hashCode(this.dataModificacao);
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
        final Admnistrador other = (Admnistrador) obj;
        if (this.idAdmnistrador != other.idAdmnistrador) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.franquia, other.franquia)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        return Objects.equals(this.dataModificacao, other.dataModificacao);
    }

    @Override
    public String toString() {
        DateTimeFormatter fd = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        if (dataModificacao == null) {
            return "Admnistrador: " + this.pessoa.getNomePessoa() + "\n"
                    + "Cpf do Admnistrador: " + this.pessoa.getCpf() + "\n"
                    + "Franquia: " + this.franquia.getNomeFranquia() + "\n"
                    + "Cnpj: " + this.franquia.getCnpj() + "\n"
                    + "Data de criacao: " + fd.format(dataCriacao);
        } else {
            return "Admnistrador: " + this.pessoa.getNomePessoa() + "\n"
                    + "Cpf do Admnistrador: " + this.pessoa.getCpf() + "\n"
                    + "Franquia: " + this.franquia.getNomeFranquia() + "\n"
                    + "Cnpj: " + this.franquia.getCnpj() + "\n"
                    + "Data de criacao: " + fd.format(dataCriacao) + "\n"
                    + "Data de modificacao: " + fd.format(dataModificacao);
        }
    }

}
