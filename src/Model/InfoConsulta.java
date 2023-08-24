package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class InfoConsulta {

    private int idInfoConsulta;
    private Consulta consulta;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public InfoConsulta() {
    }

    public void setIdInfoConsulta(int idInfoConsulta) {
        this.idInfoConsulta = idInfoConsulta;
    }

    
    public int getIdInfoConsulta() {
        return idInfoConsulta;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public InfoConsulta(Consulta consulta, String descricao, LocalDateTime dataCriacao) {
        this.consulta = consulta;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.idInfoConsulta;
        hash = 59 * hash + Objects.hashCode(this.consulta);
        hash = 59 * hash + Objects.hashCode(this.descricao);
        hash = 59 * hash + Objects.hashCode(this.dataCriacao);
        hash = 59 * hash + Objects.hashCode(this.dataModificacao);
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
        final InfoConsulta other = (InfoConsulta) obj;
        if (this.idInfoConsulta != other.idInfoConsulta) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.consulta, other.consulta)) {
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
            return "ID - InfoConsulta: " + this.idInfoConsulta + "\n"
                    + this.consulta + "\n"
                    + "Descricao: " + "\n"
                    + this.descricao + "\n"
                    + "Data e Hora De Criacao: " + fd.format(this.dataCriacao);
        } else {
            return "ID - InfoConsulta: " + this.idInfoConsulta + "\n"
                    + this.consulta + "\n"
                    + "Descricao: " + "\n"
                    + this.descricao + "\n"
                    + "Data e Hora De Criacao: " + fd.format(this.dataCriacao) + "," + " "
                    + "Data e Hora De Modificacao: " + fd.format(this.dataModificacao) + "," + " ";
        }
    }

}
