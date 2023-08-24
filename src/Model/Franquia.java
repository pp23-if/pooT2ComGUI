package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Franquia {

    private int idFranquia;
    private String nomeFranquia;
    private String cnpj;
    private String cidade;
    private String enderecoFranquia;
    private Pessoa pessoa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Franquia() {
    }

    public void setIdFranquia(int idFranquia) {
        this.idFranquia = idFranquia;
    }
    
    
    public int getIdFranquia() {
        return idFranquia;
    }

    public String getNomeFranquia() {
        return nomeFranquia;
    }

    public void setNomeFranquia(String nomeFranquia) {
        this.nomeFranquia = nomeFranquia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEnderecoFranquia() {
        return enderecoFranquia;
    }

    public void setEnderecoFranquia(String enderecoFranquia) {
        this.enderecoFranquia = enderecoFranquia;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public Franquia(String nomeFranquia, String cnpj, String cidade,
            String enderecoFranquia, Pessoa pessoa, LocalDateTime dataCriacao) {

        this.nomeFranquia = nomeFranquia;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.enderecoFranquia = enderecoFranquia;
        this.pessoa = pessoa;
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idFranquia;
        hash = 97 * hash + Objects.hashCode(this.nomeFranquia);
        hash = 97 * hash + Objects.hashCode(this.cnpj);
        hash = 97 * hash + Objects.hashCode(this.cidade);
        hash = 97 * hash + Objects.hashCode(this.enderecoFranquia);
        hash = 97 * hash + Objects.hashCode(this.pessoa);
        hash = 97 * hash + Objects.hashCode(this.dataCriacao);
        hash = 97 * hash + Objects.hashCode(this.dataModificacao);
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
        final Franquia other = (Franquia) obj;
        if (this.idFranquia != other.idFranquia) {
            return false;
        }
        if (!Objects.equals(this.nomeFranquia, other.nomeFranquia)) {
            return false;
        }
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.enderecoFranquia, other.enderecoFranquia)) {
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
            return "ID - Franquia: " + this.idFranquia + "," + " "
                    + "Franquia: " + this.nomeFranquia + "," + " "
                    + "Cnpj: " + this.cnpj + "," + " "
                    + "Cidade: " + this.cidade + "," + " "
                    + "Endereco: " + this.enderecoFranquia + "\n"
                    + "Dono: " + pessoa.getNomePessoa() + "," + " "
                    + "Cpf: " + pessoa.getCpf() + "," + " "
                    + "Data e Hora de Criacao: " + fd.format(dataCriacao) + "," + " ";
        } else {
            return "ID - Franquia: " + this.idFranquia + "," + " "
                    + "Franquia: " + this.nomeFranquia + "," + " "
                    + "Cnpj: " + this.cnpj + "," + " "
                    + "Cidade: " + this.cidade + "," + " "
                    + "Endereco: " + this.enderecoFranquia + "\n"
                    + "Dono: " + pessoa.getNomePessoa() + "," + " "
                    + "Cpf: " + pessoa.getCpf() + "," + " "
                    + "Data e Hora de Criacao: " + fd.format(dataCriacao) + "," + " "
                    + "Data e Hora de Modificacao: " + fd.format(dataModificacao) + "," + " ";
        }

    }

}
