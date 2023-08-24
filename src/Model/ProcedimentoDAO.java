package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class ProcedimentoDAO {

    private List<Procedimento> listaProcedimento = new LinkedList();

    public ProcedimentoDAO(PessoaDAO pessoaDAO, MedicoDAO medicoDAO, UnidadeFranquiaDAO unidadeFranquiaDAO,
            CalendarioSistema calendarioSistema, ConsultaDAO consultaDAO) {
    }

    public void mostraTodosProcedimentos() {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null) {
                System.out.println(procedimento + "\n");
            }
        }
    }

    public Procedimento buscaProcedimentoPorPaciente(Pessoa pessoa) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getConsulta().getPessoa().equals(pessoa)) {
                System.out.println(procedimento + "\n");
            }
        }
        return null;
    }

    public Procedimento buscaProcedimentoPorMedico(Medico medico) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getConsulta().getMedico().equals(medico)) {
                System.out.println(procedimento + "\n");
            }
        }
        return null;
    }

    public Procedimento buscaProcedimentoPorFranquia(Franquia franquia) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getConsulta()
                    .getUnidadeFranquia().getFranquia().equals(franquia)) {
                System.out.println(procedimento + "\n");
            }
        }
        return null;
    }

    public Procedimento buscaProcedimentoPorId(int idProcediemnto) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getIdProcedimento() == idProcediemnto) {
                return procedimento;
            }
        }
        return null;
    }

    public boolean verificaDisponibilidadeDataEHoraProcedimentoMedico(LocalDate diaProcedimento, LocalTime horaProcedimento,
            Medico medico) {

        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getDiaProcedimento().equals(diaProcedimento)
                    && procedimento.getHoraProcedimento().equals(horaProcedimento)
                    && procedimento.getConsulta().getMedico().equals(medico)) {

                return true;
            }
        }
        return false;
    }

    public boolean verificaDataProcedimento(CalendarioSistema calendarioSistema, LocalDate novoDiaProcedimento) {
        if (novoDiaProcedimento.isBefore(calendarioSistema.getDiaDoSistema())) {
            return true;
        }
        return false;
    }

    public Procedimento buscaProcedimentosQueTemMedicoSolicitanteEPacienteEscolhido(Pessoa pessoa, Medico medico) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null
                    && procedimento.getConsulta().getMedico().equals(medico)
                    && procedimento.getConsulta().getPessoa().equals(pessoa)) {
                System.out.println(procedimento + "\n");
            }
        }
        return null;
    }

    public Procedimento buscaProcedimentoNaoRealizado(Medico medico, CalendarioSistema calendarioSistema) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null
                    && procedimento.getConsulta().getMedico().equals(medico)
                    && procedimento.getEstadoProcedimento().equals("Agendado")
                    && procedimento.getDiaProcedimento().isEqual(calendarioSistema.getDiaDoSistema())) {
                return procedimento;
            }

        }
        return null;
    }

    public boolean cancelaProcedimentosNaoRealizadosNoDia(CalendarioSistema calendarioSistema) {

        boolean canceladas = false;
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getEstadoProcedimento().equals("Agendado")
                    && calendarioSistema.getDiaDoSistema().isAfter(procedimento.getDiaProcedimento())) {
                procedimento.setEstadoProcedimento("Cancelado");
                procedimento.setDataModificacao(calendarioSistema.getDataHoraSistema());

                if (cancelaProcedimentoNoBancoDeDados(procedimento, calendarioSistema) == true) {
                    canceladas = true;
                }

            }

        }
        return canceladas == true;

    }

    public double calculaValorProcedimentosDoMes(Medico medico, CalendarioSistema calendarioSistema, Franquia franquia) {

        double totalProcedimentos = 0;

        int mesSitemaComparavel = calendarioSistema.getDiaDoSistema().minusDays(1).getMonthValue();

        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null
                    && procedimento.getConsulta().getMedico().equals(medico)
                    && procedimento.getEstadoProcedimento().equals("Realizado")
                    && procedimento.getConsulta().getUnidadeFranquia().getFranquia().equals(franquia)
                    && procedimento.getDiaProcedimento().getMonthValue() == mesSitemaComparavel) {

                totalProcedimentos += procedimento.getValorProcedimento();
            }

        }

        return totalProcedimentos;
    }

    public double calculaValorProcedimentosPorUnidadeFranquia(Medico medico, UnidadeFranquia unidadeFranquia) {

        double totalProcedimentos = 0;

        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null
                    && procedimento.getConsulta().getMedico().equals(medico)
                    && procedimento.getEstadoProcedimento().equals("Realizado")
                    && procedimento.getConsulta().getUnidadeFranquia().equals(unidadeFranquia)) {

                totalProcedimentos += procedimento.getValorProcedimento();
            }

        }

        return totalProcedimentos;
    }

    public double calculaValorProcedimentosPorUnidadeFranquiaMes(Medico medico, UnidadeFranquia unidadeFranquia, int numeroMes) {

        double totalProcedimentos = 0;

        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null
                    && procedimento.getConsulta().getMedico().equals(medico)
                    && procedimento.getEstadoProcedimento().equals("Realizado")
                    && procedimento.getConsulta().getUnidadeFranquia().equals(unidadeFranquia)
                    && procedimento.getDiaProcedimento().getMonthValue() == numeroMes) {

                totalProcedimentos += procedimento.getValorProcedimento();
            }

        }

        return totalProcedimentos;
    }

    public double calculaParteDescontoProcedimentos(double valorProcedimentos) {
        double valorParteProcedimento;

        valorParteProcedimento = valorProcedimentos * 0.50;

        return valorParteProcedimento;
    }

    public boolean insereProcedimentoNoBancoDeDados(Consulta consulta, Procedimento procedimento) {

        boolean adicionado = true;

        String insereProcedimento = "insert into procedimento (nomeprocedimento, idconsulta, diaprocedimento, "
                + "horaprocedimento, estadoprocedimento, valorprocedimento, laudo, datacriacao) values (?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmInsereProcedimento = connection.prepareStatement(insereProcedimento)) {

                pstmInsereProcedimento.setString(1, procedimento.getNomeProcedimento());
                pstmInsereProcedimento.setInt(2, consulta.getIdConsulta());
                pstmInsereProcedimento.setDate(3, Date.valueOf(procedimento.getDiaProcedimento().plusDays(1)));
                pstmInsereProcedimento.setTime(4, Time.valueOf(procedimento.getHoraProcedimento()));
                pstmInsereProcedimento.setString(5, procedimento.getEstadoProcedimento());
                pstmInsereProcedimento.setDouble(6, procedimento.getValorProcedimento());
                pstmInsereProcedimento.setString(7, procedimento.getLaudo());
                pstmInsereProcedimento.setTimestamp(8, Timestamp.valueOf(procedimento.getDataCriacao()));

                pstmInsereProcedimento.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                adicionado = false;

            }

        } catch (SQLException erro) {

        }

        return adicionado != false;

    }

    public void BuscaProcedimentosNoBancoDeDados(ConsultaDAO consultaDAO) {

        listaProcedimento.clear();

        String buscaProcedimento = "select * from procedimento;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados(); 
                PreparedStatement pstm = connection.prepareStatement(buscaProcedimento);
                ResultSet rs = pstm.executeQuery(buscaProcedimento)) {

            while (rs.next()) {

                Procedimento procedimento = new Procedimento();

                procedimento.setIdProcedimento(rs.getInt("idprocedimento"));
                procedimento.setNomeProcedimento(rs.getString("nomeprocedimento"));

                int idConsulta = rs.getInt("idconsulta");
                Consulta consulta = consultaDAO.buscaConsultaPorId(idConsulta);

                procedimento.setConsulta(consulta);

                Date diaProcedimento = rs.getDate("diaprocedimento");
                procedimento.setDiaProcedimento(diaProcedimento.toLocalDate());

                Time horaProcedimento = rs.getTime("horaprocedimento");
                procedimento.setHoraProcedimento(horaProcedimento.toLocalTime());

                procedimento.setEstadoProcedimento(rs.getString("estadoprocedimento"));
                procedimento.setValorProcedimento(rs.getDouble("valorprocedimento"));
                procedimento.setLaudo(rs.getString("laudo"));

                Timestamp dataCriacaoProcedimento = rs.getTimestamp("datacriacao");
                procedimento.setDataCriacao(dataCriacaoProcedimento.toLocalDateTime());

                Timestamp dataModificacaoProcedimento = rs.getTimestamp("datamodificacao");

                if (dataModificacaoProcedimento != null) {
                    procedimento.setDataModificacao(dataModificacaoProcedimento.toLocalDateTime());
                }

                listaProcedimento.add(procedimento);

            }

        } catch (SQLException erro) {
        }

    }

    public boolean cancelaProcedimentoNoBancoDeDados(Procedimento procedimento, 
            CalendarioSistema calendarioSistema) {

        boolean cancelado = true;

        String cancelaProcedimento = "update procedimento set estadoprocedimento = ? where idprocedimento = ?";

        String atualizaDataAlteracaoProcedimento = "update procedimento set datamodificacao = ? where idprocedimento = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmCancelaProcedimento = connection.prepareStatement(cancelaProcedimento); PreparedStatement pstmAtualizaDataAlteracaoProcedimento
                    = connection.prepareStatement(atualizaDataAlteracaoProcedimento)) {

                pstmCancelaProcedimento.setString(1, "Cancelado");
                pstmCancelaProcedimento.setInt(2, procedimento.getIdProcedimento());

                pstmCancelaProcedimento.execute();

                pstmAtualizaDataAlteracaoProcedimento.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                pstmAtualizaDataAlteracaoProcedimento.setInt(2, procedimento.getIdProcedimento());

                pstmAtualizaDataAlteracaoProcedimento.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                cancelado = false;
            }

        } catch (Exception e) {
        }

        return cancelado != false;
    }

    public boolean remarcaProcedimentoNoBancoDeDados(LocalDate novoDiaProcedimento, LocalTime novaHoraProcedimento,
            Procedimento procedimento, CalendarioSistema calendarioSistema) {

        boolean remarcado = true;

        String remarcaDiaProcedimento = "update procedimento set diaprocedimento  = ? where idprocedimento = ?";

        String remarcaHoraProcedimento = "update procedimento set horaprocedimento = ? where idprocedimento = ?";

        String atualizaDataAlteracaoProcedimento = "update procedimento set datamodificacao = ? where idprocedimento = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmRemarcaDiaProcedimento = connection.prepareStatement(remarcaDiaProcedimento); PreparedStatement pstmRemarcaHoraProcedimento = connection.prepareStatement(remarcaHoraProcedimento); PreparedStatement pstmAtualizaDataAlteracaoProcedimento
                    = connection.prepareStatement(atualizaDataAlteracaoProcedimento)) {

                pstmRemarcaDiaProcedimento.setDate(1, Date.valueOf(novoDiaProcedimento));
                pstmRemarcaDiaProcedimento.setInt(2, procedimento.getIdProcedimento());

                pstmRemarcaDiaProcedimento.execute();

                pstmRemarcaHoraProcedimento.setTime(1, Time.valueOf(novaHoraProcedimento));
                pstmRemarcaHoraProcedimento.setInt(2, procedimento.getIdProcedimento());

                pstmRemarcaHoraProcedimento.execute();

                pstmAtualizaDataAlteracaoProcedimento.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                pstmAtualizaDataAlteracaoProcedimento.setInt(2, procedimento.getIdProcedimento());

                pstmAtualizaDataAlteracaoProcedimento.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                remarcado = false;
            }

        } catch (Exception e) {
        }

        return remarcado != false;
    }

    public boolean realizaProcedimentoNoBancoDeDados(Procedimento procedimento,
            CalendarioSistema calendarioSistema, String laudo) {

        boolean realizado = true;

        String atualizaEstadoProcedimento = "update procedimento set estadoprocedimento = ? where idprocedimento "
                + " = ? ";

        String atualizaLaudoProcedimento = "update procedimento set laudo = ? where idprocedimento "
                + " = ? ";

        String atualizaDataModificacaoProcedimento = "update procedimento set datamodificacao = ? where idprocedimento "
                + " = ? ";

        String insereFinanceiroADM = "insert into financeiroadm (tipomovimento, valorfinanceiroadm,"
                + "idunidadefranquia, descritivomovimento, datacriacao) values (?,?,?,?,?)";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaEstadoProcedimento = connection.prepareStatement(atualizaEstadoProcedimento); PreparedStatement pstmAtualizaLaudoProcedimento = connection.prepareStatement(atualizaLaudoProcedimento); PreparedStatement pstmAtualizaDataModificacaoProcedimento = connection.prepareStatement(atualizaDataModificacaoProcedimento); PreparedStatement pstmInsereFinanceiroADM = connection.prepareStatement(insereFinanceiroADM)) {

                pstmAtualizaEstadoProcedimento.setString(1, "Realizado");
                pstmAtualizaEstadoProcedimento.setInt(2, procedimento.getIdProcedimento());

                pstmAtualizaEstadoProcedimento.execute();

                pstmAtualizaLaudoProcedimento.setString(1, laudo);
                pstmAtualizaLaudoProcedimento.setInt(2, procedimento.getIdProcedimento());

                pstmAtualizaLaudoProcedimento.execute();

                pstmAtualizaDataModificacaoProcedimento.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                pstmAtualizaDataModificacaoProcedimento.setInt(2, procedimento.getIdProcedimento());

                pstmAtualizaDataModificacaoProcedimento.execute();

                pstmInsereFinanceiroADM.setString(1, "Entrada");
                pstmInsereFinanceiroADM.setDouble(2, procedimento.getValorProcedimento());
                pstmInsereFinanceiroADM.setInt(3, procedimento.getConsulta().getUnidadeFranquia().getIdUnidadeFranquia());
                pstmInsereFinanceiroADM.setString(4, "Procedimento");
                pstmInsereFinanceiroADM.setTimestamp(5, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));

                pstmInsereFinanceiroADM.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                realizado = false;
            }

        } catch (Exception e) {
        }

        return realizado != false;
    }
    
}
