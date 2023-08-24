package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Consulta {

    private int idConsulta;
    private LocalDate diaConsulta;
    private LocalTime horaConsulta;
    private String estadoConsulta = "";
    private Medico medico;
    private Pessoa pessoa;
    private UnidadeFranquia unidadeFranquia;
    private double valor;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Consulta() {
    }
    
    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    
    public LocalDate getDiaConsulta() {
        return diaConsulta;
    }

    public void setDiaConsulta(LocalDate diaConsulta) {
        this.diaConsulta = diaConsulta;
    }

    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(LocalTime horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public String getEstadoConsulta() {
        return estadoConsulta;
    }

    public void setEstadoConsulta(String estadoConsulta) {
        this.estadoConsulta = estadoConsulta;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public UnidadeFranquia getUnidadeFranquia() {
        return unidadeFranquia;
    }

    public void setUnidadeFranquia(UnidadeFranquia unidadeFranquia) {
        this.unidadeFranquia = unidadeFranquia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public Consulta(LocalDate diaConsulta, LocalTime horaConsulta, Medico medico,
            Pessoa pessoa, UnidadeFranquia unidadeFranquia,
            double valor, String estado, LocalDateTime dataCriacao) {

        this.diaConsulta = diaConsulta;
        this.horaConsulta = horaConsulta;
        this.medico = medico;
        this.pessoa = pessoa;
        this.unidadeFranquia = unidadeFranquia;
        this.valor = valor;
        this.estadoConsulta = estado;
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idConsulta;
        hash = 79 * hash + Objects.hashCode(this.diaConsulta);
        hash = 79 * hash + Objects.hashCode(this.horaConsulta);
        hash = 79 * hash + Objects.hashCode(this.estadoConsulta);
        hash = 79 * hash + Objects.hashCode(this.medico);
        hash = 79 * hash + Objects.hashCode(this.pessoa);
        hash = 79 * hash + Objects.hashCode(this.unidadeFranquia);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
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
        final Consulta other = (Consulta) obj;
        if (this.idConsulta != other.idConsulta) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.estadoConsulta, other.estadoConsulta)) {
            return false;
        }
        if (!Objects.equals(this.diaConsulta, other.diaConsulta)) {
            return false;
        }
        if (!Objects.equals(this.horaConsulta, other.horaConsulta)) {
            return false;
        }
        if (!Objects.equals(this.medico, other.medico)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.unidadeFranquia, other.unidadeFranquia)) {
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
        DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fdhora = DateTimeFormatter.ofPattern("HH:mm");

        if (dataModificacao == null) {
            return "ID - Consulta: " + this.idConsulta + "," + " "
                    + "Paciente: " + this.pessoa.getNomePessoa() + "," + " "
                    + "Cpf: " + this.pessoa.getCpf() + "," + " "
                    + "Medico: " + this.medico.getPessoa().getNomePessoa() + "," + " " + "\n"
                    + "Crm: " + this.medico.getCrm() + "," + " "
                    + "Especialidade: " + this.medico.getEspecialidade() + "," + " " + "\n"
                    + "Unidade Da Franquia: " + this.unidadeFranquia.getFranquia().getNomeFranquia() + "," + " "
                    + "Cidade: " + this.unidadeFranquia.getCidadeUnidadeFranquia() + "," + " "
                    + "Endereco: " + this.unidadeFranquia.getEnderecoUnidadeFranquia() + "," + " " + "\n"
                    + "Dia: " + fdia.format(this.diaConsulta) + "," + " "
                    + "Hora: " + fdhora.format(this.horaConsulta) + "," + " "
                    + "Valor: " + this.valor + "," + " "
                    + "Estado: " + this.estadoConsulta + "," + " "
                    + "Data e Hora De Criacao: " + fd.format(this.dataCriacao) + "," + " ";

        } else {
            return "ID - Consulta: " + this.idConsulta + "," + " "
                    + "Paciente: " + this.pessoa.getNomePessoa() + "," + " "
                    + "Cpf: " + this.pessoa.getCpf() + "," + " "
                    + "Medico: " + this.medico.getPessoa().getNomePessoa() + "," + " " + "\n"
                    + "Crm: " + this.medico.getCrm() + "," + " "
                    + "Especialidade: " + this.medico.getEspecialidade() + "," + " " + "\n"
                    + "Unidade Da Franquia: " + this.unidadeFranquia.getFranquia().getNomeFranquia() + "," + " "
                    + "Cidade: " + this.unidadeFranquia.getCidadeUnidadeFranquia() + "," + " "
                    + "Endereco: " + this.unidadeFranquia.getEnderecoUnidadeFranquia() + "," + " " + "\n"
                    + "Dia: " + fdia.format(this.diaConsulta) + "," + " "
                    + "Hora: " + fdhora.format(this.horaConsulta) + "," + " "
                    + "Valor: " + this.valor + "," + " "
                    + "Estado: " + this.estadoConsulta + "," + " "
                    + "Data e Hora De Criacao: " + fd.format(this.dataCriacao) + "," + " "
                    + "Data e Hora De Modificacao: " + fd.format(dataModificacao) + "," + " ";

        }
    }

}
