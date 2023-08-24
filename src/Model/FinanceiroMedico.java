package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FinanceiroMedico {

    private int idFinanceiroMedico;
    private double valor;
    private Medico medico;
    private String Estado;
    private Franquia franquia;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public FinanceiroMedico() {
    }

    public void setIdFinanceiroMedico(int idFinanceiroMedico) {
        this.idFinanceiroMedico = idFinanceiroMedico;
    }

    public int getIdFinanceiroMedico() {
        return idFinanceiroMedico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
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

    public FinanceiroMedico(double valor, Medico medico, String Estado, Franquia franquia, LocalDateTime dataCriacao) {

        this.valor = valor;
        this.medico = medico;
        this.Estado = Estado;
        this.franquia = franquia;
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.idFinanceiroMedico;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.medico);
        hash = 47 * hash + Objects.hashCode(this.Estado);
        hash = 47 * hash + Objects.hashCode(this.franquia);
        hash = 47 * hash + Objects.hashCode(this.dataCriacao);
        hash = 47 * hash + Objects.hashCode(this.dataModificacao);
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
        final FinanceiroMedico other = (FinanceiroMedico) obj;
        if (this.idFinanceiroMedico != other.idFinanceiroMedico) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.Estado, other.Estado)) {
            return false;
        }
        if (!Objects.equals(this.medico, other.medico)) {
            return false;
        }
        if (!Objects.equals(this.franquia, other.franquia)) {
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
            return "ID - Financeiro Medico: " + this.idFinanceiroMedico + "," + " "
                    + "Medico: " + this.medico.getPessoa().getNomePessoa() + "," + " "
                    + "Crm: " + this.medico.getCrm() + "," + " "
                    + "Estado: " + this.Estado + "," + " "
                    + "valor: " + this.valor + "\n"
                    + "Franquia: " + this.franquia + "\n"
                    + "Data e Hora Criacao: " + fd.format(this.dataCriacao) + "," + " ";
        } else {
            return "ID - Financeiro Medico: " + this.idFinanceiroMedico + "," + " "
                    + "Medico: " + this.medico.getPessoa().getNomePessoa() + "," + " "
                    + "Crm: " + this.medico.getCrm() + "," + " "
                    + "Estado: " + this.Estado + "," + " "
                    + "valor: " + this.valor + "\n"
                    + "Franquia: " + this.franquia + "\n"
                    + "Data e Hora Criacao: " + fd.format(this.dataCriacao) + "," + " "
                    + "Data e Hora Modificao: " + fd.format(this.dataModificacao);
        }

    }

}
