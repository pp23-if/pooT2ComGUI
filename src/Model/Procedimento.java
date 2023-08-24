package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Procedimento {

    private int idProcedimento;
    private String nomeProcedimento;
    private Consulta consulta;
    private LocalDate diaProcedimento;
    private LocalTime horaProcedimento;
    private String estadoProcedimento;
    private double valorProcedimento;
    private String laudo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Procedimento() {
    }

    public void setIdProcedimento(int idProcedimento) {
        this.idProcedimento = idProcedimento;
    }
    
    
    public int getIdProcedimento() {
        return idProcedimento;
    }

    public String getNomeProcedimento() {
        return nomeProcedimento;
    }

    public void setNomeProcedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public LocalDate getDiaProcedimento() {
        return diaProcedimento;
    }

    public void setDiaProcedimento(LocalDate diaProcedimento) {
        this.diaProcedimento = diaProcedimento;
    }

    public LocalTime getHoraProcedimento() {
        return horaProcedimento;
    }

    public void setHoraProcedimento(LocalTime horaProcedimento) {
        this.horaProcedimento = horaProcedimento;
    }

    public String getEstadoProcedimento() {
        return estadoProcedimento;
    }

    public void setEstadoProcedimento(String estadoProcedimento) {
        this.estadoProcedimento = estadoProcedimento;
    }

    public double getValorProcedimento() {
        return valorProcedimento;
    }

    public void setValorProcedimento(double valorProcedimento) {
        this.valorProcedimento = valorProcedimento;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
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

    public Procedimento(String nomeProcedimento, Consulta consulta, LocalDate diaProcedimento, LocalTime horaProcedimento,
            String estadoProcedimento, double valorProcedimento, String laudo, LocalDateTime dataCriacao) {
        this.nomeProcedimento = nomeProcedimento;
        this.consulta = consulta;
        this.diaProcedimento = diaProcedimento;
        this.horaProcedimento = horaProcedimento;
        this.estadoProcedimento = estadoProcedimento;
        this.valorProcedimento = valorProcedimento;
        this.laudo = laudo;
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.idProcedimento;
        hash = 47 * hash + Objects.hashCode(this.nomeProcedimento);
        hash = 47 * hash + Objects.hashCode(this.consulta);
        hash = 47 * hash + Objects.hashCode(this.diaProcedimento);
        hash = 47 * hash + Objects.hashCode(this.horaProcedimento);
        hash = 47 * hash + Objects.hashCode(this.estadoProcedimento);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.valorProcedimento) ^ (Double.doubleToLongBits(this.valorProcedimento) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.laudo);
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
        final Procedimento other = (Procedimento) obj;
        if (this.idProcedimento != other.idProcedimento) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorProcedimento) != Double.doubleToLongBits(other.valorProcedimento)) {
            return false;
        }
        if (!Objects.equals(this.nomeProcedimento, other.nomeProcedimento)) {
            return false;
        }
        if (!Objects.equals(this.estadoProcedimento, other.estadoProcedimento)) {
            return false;
        }
        if (!Objects.equals(this.laudo, other.laudo)) {
            return false;
        }
        if (!Objects.equals(this.consulta, other.consulta)) {
            return false;
        }
        if (!Objects.equals(this.diaProcedimento, other.diaProcedimento)) {
            return false;
        }
        if (!Objects.equals(this.horaProcedimento, other.horaProcedimento)) {
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

        DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fdhora = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter fd = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        if (dataModificacao == null) {

            return "ID - Procedimento: " + this.idProcedimento + "\n"
                    + "Consulta Vinculada: " + "\n"
                    + this.consulta + "\n"
                    + "Procediemnto: " + this.nomeProcedimento + "," + " "
                    + "Dia: " + fdia.format(this.diaProcedimento) + "," + " "
                    + "Hora: " + fdhora.format(this.horaProcedimento) + "," + " "
                    + "Estado: " + this.estadoProcedimento + "," + " "
                    + "Valor: " + this.valorProcedimento + "\n"
                    + "Laudo: " + "\n"
                    + this.laudo + "\n"
                    + "Data de criacao: " + fd.format(this.dataCriacao) + "," + " ";

        } else {
            return "ID - Procedimento: " + this.idProcedimento + "\n"
                    + "Consulta Vinculada: " + "\n"
                    + this.consulta + "\n"
                    + "Procediemnto: " + this.nomeProcedimento + "," + " "
                    + "Dia: " + fdia.format(this.diaProcedimento) + "," + " "
                    + "Hora: " + fdhora.format(this.horaProcedimento) + "," + " "
                    + "Estado: " + this.estadoProcedimento + "," + " "
                    + "Valor: " + this.valorProcedimento + "\n"
                    + "Laudo: " + "\n"
                    + this.laudo + "\n"
                    + "Data de Criacao: " + fd.format(this.dataCriacao) + "," + " "
                    + "Data e Hora de Modificacao: " + fd.format(this.dataModificacao) + "," + " ";
        }
    }

}
