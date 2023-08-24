package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Pessoa {

    private int idPessoa;
    private String nomePessoa;
    private String cpf;
    private String enderecoPessoa;
    private String telefonePessoa;
    private String loginPessoa;
    private String senhaPessoa;
    private String tipoUsuario;
    private boolean habilitado;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Pessoa() {
    }
    
    public int getId() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEnderecoPessoa() {
        return enderecoPessoa;
    }

    public void setEnderecoPessoa(String enderecoPessoa) {
        this.enderecoPessoa = enderecoPessoa;
    }

    public String getTelefonePessoa() {
        return telefonePessoa;
    }

    public void setTelefonePessoa(String telefonePessoa) {
        this.telefonePessoa = telefonePessoa;
    }

    public String getLoginPessoa() {
        return loginPessoa;
    }

    public void setLoginPessoa(String loginPessoa) {
        this.loginPessoa = loginPessoa;
    }

    public String getSenhaPessoa() {
        return senhaPessoa;
    }

    public void setSenhaPessoa(String senhaPessoa) {
        this.senhaPessoa = senhaPessoa;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
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
    
      public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Pessoa(String nomePessoa, String cpf, String enderecoPessoa, String telefonePessoa,
            String loginPessoa, String senhaPessoa, String tipoUsuario, LocalDateTime dataCriacao) {
        
        this.nomePessoa = nomePessoa;
        this.cpf = cpf;
        this.enderecoPessoa = enderecoPessoa;
        this.telefonePessoa = telefonePessoa;
        this.loginPessoa = loginPessoa;
        this.senhaPessoa = senhaPessoa;
        this.tipoUsuario = tipoUsuario;
        this.dataCriacao = dataCriacao;
        this.habilitado = true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idPessoa;
        hash = 29 * hash + Objects.hashCode(this.nomePessoa);
        hash = 29 * hash + Objects.hashCode(this.cpf);
        hash = 29 * hash + Objects.hashCode(this.enderecoPessoa);
        hash = 29 * hash + Objects.hashCode(this.telefonePessoa);
        hash = 29 * hash + Objects.hashCode(this.loginPessoa);
        hash = 29 * hash + Objects.hashCode(this.senhaPessoa);
        hash = 29 * hash + Objects.hashCode(this.tipoUsuario);
        hash = 29 * hash + Objects.hashCode(this.dataCriacao);
        hash = 29 * hash + Objects.hashCode(this.dataModificacao);
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
        final Pessoa other = (Pessoa) obj;
        if (this.idPessoa != other.idPessoa) {
            return false;
        }
        if (!Objects.equals(this.nomePessoa, other.nomePessoa)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.enderecoPessoa, other.enderecoPessoa)) {
            return false;
        }
        if (!Objects.equals(this.telefonePessoa, other.telefonePessoa)) {
            return false;
        }
        if (!Objects.equals(this.loginPessoa, other.loginPessoa)) {
            return false;
        }
        if (!Objects.equals(this.senhaPessoa, other.senhaPessoa)) {
            return false;
        }
        if (!Objects.equals(this.tipoUsuario, other.tipoUsuario)) {
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

        if (this.dataModificacao == null) {
            return "ID - Pessoa: " + this.idPessoa + "," + " "
                    + "Nome: " + this.nomePessoa + "," + " "
                    + "Cpf: " + this.cpf + "," + " "
                    + "Endereco: " + this.enderecoPessoa + "," + " " 
                    + "Login: " + this.loginPessoa + "," + " "
                    + "Senha: " + this.senhaPessoa + "," + " " 
                    + "Telefone: " + this.telefonePessoa + "," + "\n"
                    + "Tipo de Usuario: " + this.tipoUsuario + "," + " "
                    + "Data e Hora da Criacao: " + fd.format(this.dataCriacao) + "," + " ";
        } else {
            return "ID - Pessoa: " + this.idPessoa + "," + " "
                    + "Nome: " + this.nomePessoa + "," + " "
                    + "Cpf: " + this.cpf + "," + " "
                    + "Endereco: " + this.enderecoPessoa + "," + " " 
                    + "Login: " + this.loginPessoa + "," + " "
                    + "Senha: " + this.senhaPessoa + "," + " " 
                    + "Telefone: " + this.telefonePessoa + "," + " " + "\n"
                    + "Tipo de Usuario: " + this.tipoUsuario + "," + " "
                    + "Data e Hora da Criacao: " + fd.format(this.dataCriacao) + "," + " "
                    + "Data e Hora da Modificacao: " + fd.format(this.dataModificacao) + "," + " ";
        }

    }

  
}
