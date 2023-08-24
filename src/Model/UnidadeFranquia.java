package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UnidadeFranquia {

    private int idUnidadeFranquia;
    private Franquia franquia;
    private String cidadeUnidadeFranquia;
    private String enderecoUnidadeFranquia;
    private Pessoa pessoa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public UnidadeFranquia() {
    }

    public void setIdUnidadeFranquia(int idUnidadeFranquia) {
        this.idUnidadeFranquia = idUnidadeFranquia;
    }
    
    
    public int getIdUnidadeFranquia() {
        return idUnidadeFranquia;
    }

    public Franquia getFranquia() {
        return franquia;
    }

    public void setFranquia(Franquia franquia) {
        this.franquia = franquia;
    }

    public String getCidadeUnidadeFranquia() {
        return cidadeUnidadeFranquia;
    }

    public void setCidadeUnidadeFranquia(String cidadeUnidadeFranquia) {
        this.cidadeUnidadeFranquia = cidadeUnidadeFranquia;
    }

    public String getEnderecoUnidadeFranquia() {
        return enderecoUnidadeFranquia;
    }

    public void setEnderecoUnidadeFranquia(String enderecoUnidadeFranquia) {
        this.enderecoUnidadeFranquia = enderecoUnidadeFranquia;
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

    public UnidadeFranquia(Franquia franquia, String cidadeUnidadeFranquia,
            String enderecoUnidadeFranquia, Pessoa pessoa, LocalDateTime dataCriacao) {
        
        this.franquia = franquia;
        this.cidadeUnidadeFranquia = cidadeUnidadeFranquia;
        this.enderecoUnidadeFranquia = enderecoUnidadeFranquia;
        this.pessoa = pessoa;
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idUnidadeFranquia;
        hash = 53 * hash + Objects.hashCode(this.franquia);
        hash = 53 * hash + Objects.hashCode(this.cidadeUnidadeFranquia);
        hash = 53 * hash + Objects.hashCode(this.enderecoUnidadeFranquia);
        hash = 53 * hash + Objects.hashCode(this.pessoa);
        hash = 53 * hash + Objects.hashCode(this.dataCriacao);
        hash = 53 * hash + Objects.hashCode(this.dataModificacao);
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
        final UnidadeFranquia other = (UnidadeFranquia) obj;
        if (this.idUnidadeFranquia != other.idUnidadeFranquia) {
            return false;
        }
        if (!Objects.equals(this.cidadeUnidadeFranquia, other.cidadeUnidadeFranquia)) {
            return false;
        }
        if (!Objects.equals(this.enderecoUnidadeFranquia, other.enderecoUnidadeFranquia)) {
            return false;
        }
        if (!Objects.equals(this.franquia, other.franquia)) {
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
            return "ID - UnidadeFranquia: " + this.idUnidadeFranquia + "," + " "
                    + "Dono Da Unidade: " + this.getPessoa().getNomePessoa() + "," + " "
                    + "Cpf: " + this.getPessoa().getCpf() + "," + " "
                    + "Franquia: " + this.franquia.getNomeFranquia() + "\n"
                    + "Cidade: " + this.cidadeUnidadeFranquia + "," + " "
                    + "Endereco: " + this.enderecoUnidadeFranquia + "," + " "
                    + "Data e Hora de Criacao: " + fd.format(this.dataCriacao) + "," + " ";
        } else {
            return "ID - UnidadeFranquia: " + this.idUnidadeFranquia + "," + " "
                    + "Dono Da Unidade: " + this.getPessoa().getNomePessoa() + "," + " "
                    + "Cpf: " + this.getPessoa().getCpf() + "," + " "
                    + "Franquia: " + this.franquia.getNomeFranquia() + "\n"
                    + "Cidade: " + this.cidadeUnidadeFranquia + "," + " "
                    + "Endereco: " + this.enderecoUnidadeFranquia + "," + " "
                    + "Data e Hora de Criacao: " + fd.format(this.dataCriacao) + "," + " "
                    + "Data e Hora de Modificacao: " + fd.format(this.dataModificacao) + "," + " ";
        }
    }

}
