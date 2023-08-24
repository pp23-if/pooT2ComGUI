package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FinanceiroAdm {

    private int idFinanceiroAdm;
    private String tipoMovimento;
    private double valor;
    private UnidadeFranquia unidadeFranquia;
    private String descritivoMovimento;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public FinanceiroAdm() {
    }

    public void setIdFinanceiroAdm(int idFinanceiroAdm) {
        this.idFinanceiroAdm = idFinanceiroAdm;
    }
    
    public int getIdFinanceiroAdm() {
        return idFinanceiroAdm;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public UnidadeFranquia getUnidadeFranquia() {
        return unidadeFranquia;
    }

    public void setUnidadeFranquia(UnidadeFranquia unidadeFranquia) {
        this.unidadeFranquia = unidadeFranquia;
    }

    public String getDescritivoMovimento() {
        return descritivoMovimento;
    }
    
    public void setDescritivoMovimento(String descritivoMovimento) {
        this.descritivoMovimento = descritivoMovimento;
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

    public FinanceiroAdm(String tipoMovimento, double valor, UnidadeFranquia unidadeFranquia,
            String descritivoMovimento, LocalDateTime dataCriacao) {
        
        this.tipoMovimento = tipoMovimento;
        this.valor = valor;
        this.unidadeFranquia = unidadeFranquia;
        this.descritivoMovimento = descritivoMovimento;
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idFinanceiroAdm;
        hash = 79 * hash + Objects.hashCode(this.tipoMovimento);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.unidadeFranquia);
        hash = 79 * hash + Objects.hashCode(this.descritivoMovimento);
        hash = 79 * hash + Objects.hashCode(this.dataCriacao);
        hash = 79 * hash + Objects.hashCode(this.dataModificacao);
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
        final FinanceiroAdm other = (FinanceiroAdm) obj;
        if (this.idFinanceiroAdm != other.idFinanceiroAdm) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.tipoMovimento, other.tipoMovimento)) {
            return false;
        }
        if (!Objects.equals(this.descritivoMovimento, other.descritivoMovimento)) {
            return false;
        }
        if (!Objects.equals(this.unidadeFranquia, other.unidadeFranquia)) {
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
            return "ID - Financeiro: " + this.idFinanceiroAdm + "," + " "
                    + "Movimento: " + this.tipoMovimento + "," + " "
                    + "Descricao: " + this.descritivoMovimento + "," + " "
                    + "Valor: " + this.valor + "\n"
                    + this.unidadeFranquia + "\n"
                    + "Data e Hora Criacao: " + fd.format(this.dataCriacao) + "," + " ";
        } else {
            return "ID - Financeiro: " + this.idFinanceiroAdm + "," + " "
                    + "Movimento: " + this.tipoMovimento + "," + " "
                    + "Descricao: " + this.descritivoMovimento + "," + " "
                    + "Valor: " + this.valor + "\n"
                    + this.unidadeFranquia + "\n"
                    + "Data e Hora Criacao: " + fd.format(this.dataCriacao) + "," + " "
                    + "Data e Hora Modificao: " + fd.format(this.dataModificacao);
        }

    }

}
