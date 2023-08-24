package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class MedicoDAO {

    private List<Medico> listaMedico = new LinkedList();

    public MedicoDAO(PessoaDAO pessoaDAO, CalendarioSistema calendarioSistema) {
    }

    public void mostraTodosMedicosHabilitados() {
        for (Medico medico : listaMedico) {
            if (medico != null && medico.getPessoa().isHabilitado() == true) {
                System.out.println(medico + "\n");
            }
        }
    }

    public void mostraTodosMedicos() {
        for (Medico medico : listaMedico) {
            if (medico != null) {
                System.out.println(medico + "\n");
            }
        }
    }

    public Medico buscaMedico(Medico m) {

        for (Medico medico : listaMedico) {

            if (medico != null && medico.equals(m)) {
                return medico;
            }
        }
        return null;
    }

    public Medico buscaMedicoAtravesdaPessoaVinculada(Pessoa pessoaLogada) {

        for (Medico medico : listaMedico) {
            if (medico != null && medico.getPessoa().equals(pessoaLogada)) {
                return medico;
            }
        }

        return null;
    }

    public Medico buscaMedicoPorCrm(String crm) {
        for (Medico medico : listaMedico) {

            if (medico != null && medico.getCrm().equals(crm)) {
                return medico;
            }
        }
        return null;

    }

    public boolean atualizaSenhaMedico(Medico m, String novaSenha, CalendarioSistema calendarioSistema) {

        for (Medico medico : listaMedico) {

            if (medico != null && medico.equals(m)) {
                medico.getPessoa().setSenhaPessoa(novaSenha);
                medico.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
                return true;
            }
        }

        return false;
    }

    public boolean atualizaTelefoneMedico(Medico m, String novoTelefone, CalendarioSistema calendarioSistema) {

        if (!verificaSeTelefoneEstaSendoUsado(novoTelefone) == true) {
            for (Medico medico : listaMedico) {

                if (medico != null && medico.equals(m)) {
                    medico.getPessoa().setTelefonePessoa(novoTelefone);
                    medico.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
                    return true;
                }
            }
        }

        return false;
    }

    public boolean verificaSeloginEstaSendoUsado(String login) {
        for (Medico medico : listaMedico) {
            if (medico != null && medico.getPessoa().getLoginPessoa().equals(login)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaSeTelefoneEstaSendoUsado(String telefone) {
        for (Medico medico : listaMedico) {
            if (medico != null && medico.getPessoa().getTelefonePessoa().equals(telefone)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaSeMedicoExiste(Pessoa p) {
        for (Medico medico : listaMedico) {

            if (medico != null && medico.getPessoa().getCpf().equals(p.getCpf())) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaCrm(String Crm) {
        for (Medico medico : listaMedico) {

            if (medico != null && medico.getCrm().equals(Crm.toUpperCase())
                    || medico != null && medico.getCrm().equals(Crm.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public Medico buscaMedicoPorId(int idMedico) {
        for (Medico medico : listaMedico) {

            if (medico != null && medico.getIdMedico() == idMedico) {
                return medico;
            }
        }
        return null;
    }

    public double verificaValorConsulta(Medico medico) {
        if (medico.getEspecialidade().equals("Ortopedista")) {
            return 500;
        } else if (medico.getEspecialidade().equals("Nutricionista")) {
            return 1000;
        } else if (medico.getEspecialidade().equals("Cardiologista")) {
            return 1200;
        } else {
            return 1500;
        }

    }

    public boolean vericaSeMedicoEPacienteSaoIguais(Pessoa pessoa, Medico medico) {
        if (pessoa != null && medico != null && medico.
                getPessoa().getCpf().equals(pessoa.getCpf())) {

            return true;
        }
        return false;
    }

    public boolean excluirMedico(Medico medico, CalendarioSistema calendarioSistema) {
        if (medico != null
                && medico.getPessoa().isHabilitado() == true) {
            medico.getPessoa().setHabilitado(false);
            medico.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
            medico.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }

    public void filtraMedicosExcluidos() {
        for (Medico medico : listaMedico) {

            if (medico != null && medico.getPessoa().isHabilitado() == false) {
                System.out.println(medico + "\n");
            }
        }
    }

    public boolean reverterExclusaoMedico(Medico medico, CalendarioSistema calendarioSistema) {
        if (medico != null
                && medico.getPessoa().isHabilitado() == false) {
            medico.getPessoa().setHabilitado(true);
            medico.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
            medico.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }

    public Medico buscaMedicoExcluidoPorId(int idMedicoExcluido) {
        for (Medico medico : listaMedico) {

            if (medico != null && medico.getIdMedico() == idMedicoExcluido
                    && medico.getPessoa().isHabilitado() == false) {
                return medico;
            }
        }
        return null;
    }

    public boolean insereMedicoNoBancoDeDados(Pessoa pessoa, Medico medico) {

        boolean adicionado = true;

        String inserePessoaMedico = "insert into tipousuario (cpfpessoa,logintipousuario,senhatipousuario,"
                + "tipousuario, telefonepessoa, datacriacao, habilitado) \n"
                + "values (?,?,?,?,?,?,?)";

        String insereMedico = "insert into medico (cpfmedico,crm,especialidade,datacriacao) \n"
                + "values (?,?,?,?)";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmInsereMedico = connection.prepareStatement(insereMedico); PreparedStatement pstmInserePessoaMedico = connection.prepareStatement(inserePessoaMedico)) {

                pstmInserePessoaMedico.setString(1, pessoa.getCpf());
                pstmInserePessoaMedico.setString(2, pessoa.getLoginPessoa());
                pstmInserePessoaMedico.setString(3, pessoa.getSenhaPessoa());
                pstmInserePessoaMedico.setString(4, pessoa.getTipoUsuario());
                pstmInserePessoaMedico.setString(5, pessoa.getTelefonePessoa());
                pstmInserePessoaMedico.setTimestamp(6, Timestamp.valueOf(pessoa.getDataCriacao()));
                pstmInserePessoaMedico.setBoolean(7, pessoa.isHabilitado());

                pstmInserePessoaMedico.execute();

                pstmInsereMedico.setString(1, pessoa.getCpf());
                pstmInsereMedico.setString(2, medico.getCrm());
                pstmInsereMedico.setString(3, medico.getEspecialidade());
                pstmInsereMedico.setTimestamp(4, Timestamp.valueOf(medico.getDataCriacao()));

                pstmInsereMedico.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                adicionado = false;

            }

        } catch (SQLException erro) {

        }

        return adicionado != false;

    }

    public void BuscaMedicoNoBancoDeDados(PessoaDAO pessoaDAO) {

        listaMedico.clear();

        String buscaMedico = "select idmedico, cpfmedico, crm, especialidade, datacriacao, datamodificacao from  medico";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados(); 
                PreparedStatement pstm = connection.prepareStatement(buscaMedico); 
                ResultSet rs = pstm.executeQuery(buscaMedico)) {

            while (rs.next()) {

                Medico medico = new Medico();

                String cpfPessoa = rs.getString("cpfmedico");
                Pessoa pessoaMedico = pessoaDAO.buscaPessoaMedicoPorCpf(cpfPessoa);
                medico.setPessoa(pessoaMedico);

                medico.setIdMedico(rs.getInt("idmedico"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medico.setCrm(rs.getString("crm"));

                Timestamp medicoDataCriacao = rs.getTimestamp("datacriacao");
                medico.setDataCriacao(medicoDataCriacao.toLocalDateTime());

                Timestamp medicoDataModificacao = rs.getTimestamp("datamodificacao");

                if (medicoDataModificacao != null) {
                    medico.setDataModificacao(medicoDataModificacao.toLocalDateTime());
                }

                listaMedico.add(medico);

            }

        } catch (SQLException erro) {
        }

    }

    public boolean AtualizaLoginMedicoNoBancoDeDados(String novoLoginMedico, Medico medico,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaLoginMedico = "update tipousuario set logintipousuario = ? where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoaMedico = "update tipousuario set datamodificacao = ? "
                + "where cpfpessoa = ? and tipousuario = ?";

        if (!verificaSeloginEstaSendoUsado(novoLoginMedico) == true) {

            try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

                connection.setAutoCommit(false);

                try (PreparedStatement pstmAtualizaLoginMedico = connection.prepareStatement(atualizaLoginMedico); PreparedStatement pstmAtualizaDataAlteracaoPessoaMedico
                        = connection.prepareStatement(atualizaDataAlteracaoPessoaMedico)) {

                    pstmAtualizaLoginMedico.setString(1, novoLoginMedico);
                    pstmAtualizaLoginMedico.setString(2, medico.getPessoa().getCpf());
                    pstmAtualizaLoginMedico.setString(3, medico.getPessoa().getTipoUsuario());

                    pstmAtualizaLoginMedico.execute();

                    pstmAtualizaDataAlteracaoPessoaMedico.setTimestamp(1,
                            Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));

                    pstmAtualizaDataAlteracaoPessoaMedico.setString(2, medico.getPessoa().getCpf());
                    pstmAtualizaDataAlteracaoPessoaMedico.setString(3, medico.getPessoa().getTipoUsuario());

                    pstmAtualizaDataAlteracaoPessoaMedico.execute();

                    connection.commit();

                } catch (SQLException erro) {

                    connection.rollback();
                    atualizado = false;
                    
                }

            } catch (Exception e) {
            }

        } else {
            atualizado = false;
        }

        return atualizado != false;

    }

    public boolean AtualizaSenhaMedicoNoBancoDeDados(String novaSenhaMedico, Medico medico,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaSenhaMedico = "update tipousuario set senhatipousuario = ? where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoaMedico = "update tipousuario set datamodificacao = ? "
                + "where cpfpessoa = ? and tipousuario = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaSenhaMedico = connection.prepareStatement(atualizaSenhaMedico); PreparedStatement pstmAtualizaDataAlteracaoPessoaMedico
                    = connection.prepareStatement(atualizaDataAlteracaoPessoaMedico)) {

                pstmAtualizaSenhaMedico.setString(1, novaSenhaMedico);
                pstmAtualizaSenhaMedico.setString(2, medico.getPessoa().getCpf());
                pstmAtualizaSenhaMedico.setString(3, medico.getPessoa().getTipoUsuario());

                pstmAtualizaSenhaMedico.execute();

                pstmAtualizaDataAlteracaoPessoaMedico.setTimestamp(1,
                        Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));

                pstmAtualizaDataAlteracaoPessoaMedico.setString(2, medico.getPessoa().getCpf());
                pstmAtualizaDataAlteracaoPessoaMedico.setString(3, medico.getPessoa().getTipoUsuario());

                pstmAtualizaDataAlteracaoPessoaMedico.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                atualizado = false;
            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

    public boolean AtualizaTelefoneMedicoNoBancoDeDados(String novoTelefoneMedico, Medico medico,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaTelefoneMedico = "update tipousuario set telefonepessoa = ? where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoaMedico = "update tipousuario set datamodificacao = ? "
                + "where cpfpessoa = ? and tipousuario = ?";

        if (!verificaSeTelefoneEstaSendoUsado(novoTelefoneMedico) == true) {

            try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

                connection.setAutoCommit(false);

                try (PreparedStatement pstmAtualizaTelefoneMedico = connection.prepareStatement(atualizaTelefoneMedico); PreparedStatement pstmAtualizaDataAlteracaoPessoaMedico
                        = connection.prepareStatement(atualizaDataAlteracaoPessoaMedico)) {

                    pstmAtualizaTelefoneMedico.setString(1, novoTelefoneMedico);
                    pstmAtualizaTelefoneMedico.setString(2, medico.getPessoa().getCpf());
                    pstmAtualizaTelefoneMedico.setString(3, medico.getPessoa().getTipoUsuario());

                    pstmAtualizaTelefoneMedico.execute();

                    pstmAtualizaDataAlteracaoPessoaMedico.setTimestamp(1,
                            Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));

                    pstmAtualizaDataAlteracaoPessoaMedico.setString(2, medico.getPessoa().getCpf());
                    pstmAtualizaDataAlteracaoPessoaMedico.setString(3, medico.getPessoa().getTipoUsuario());

                    pstmAtualizaDataAlteracaoPessoaMedico.execute();

                    connection.commit();

                } catch (SQLException erro) {

                    connection.rollback();
                    atualizado = false;
                    
                }

            } catch (Exception e) {
            }

        } else {
            atualizado = false;
        }

        return atualizado != false;

    }
    
    public boolean excluiMedicoNoBancoDeDados(Pessoa pessoa, 
           CalendarioSistema calendarioSistema) {
        
        String desabilitaPaciente = "update tipousuario set habilitado = ? where idpessoa = ?";

        String atualizaDataDesabilitado = "update tipousuario set datamodificacao = ? "
                + "where idpessoa = ?";

        boolean excluido = true;

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {
            
            connection.setAutoCommit(false);
            
            try (PreparedStatement pstmDesabilitaPaciente = 
                    connection.prepareStatement(desabilitaPaciente);
                    PreparedStatement pstmAtualizaDataDesabilitado = connection.prepareStatement(atualizaDataDesabilitado);
                    ){
                
                boolean exclusao = false;
                
                
                pstmDesabilitaPaciente.setBoolean(1, exclusao);
                
                
                pstmDesabilitaPaciente.setInt(2, pessoa.getId());
                pstmDesabilitaPaciente.execute();
                
                pstmAtualizaDataDesabilitado.setTimestamp(1, 
                        Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                
                pstmAtualizaDataDesabilitado.setInt(2, pessoa.getId());
                pstmAtualizaDataDesabilitado.execute();
                
                connection.commit();

            } catch (SQLException erro) {
                connection.rollback();
                excluido = false;
                
            }
            
            
        } catch (SQLException erro) {
            excluido = false;
        }

        return excluido != false;
    }
    
     public boolean habilitaMedicoNoBancoDeDados(Pessoa pessoa, 
           CalendarioSistema calendarioSistema) {
        
        String desabilitaPaciente = "update tipousuario set habilitado = ? where idpessoa = ?";

        String atualizaDataDesabilitado = "update tipousuario set datamodificacao = ? "
                + "where idpessoa = ?";

        boolean habilitado = true;

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {
            
            connection.setAutoCommit(false);
            
            try (PreparedStatement pstmDesabilitaPaciente = 
                    connection.prepareStatement(desabilitaPaciente);
                    PreparedStatement pstmAtualizaDataDesabilitado = connection.prepareStatement(atualizaDataDesabilitado);
                    ){
                
                pstmDesabilitaPaciente.setBoolean(1, true);
                
                pstmDesabilitaPaciente.setInt(2, pessoa.getId());
                pstmDesabilitaPaciente.execute();
                
                pstmAtualizaDataDesabilitado.setTimestamp(1, 
                        Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                
                pstmAtualizaDataDesabilitado.setInt(2, pessoa.getId());
                pstmAtualizaDataDesabilitado.execute();
                
                connection.commit();

            } catch (SQLException erro) {
                connection.rollback();
                habilitado = false;
                
            }
            
            
        } catch (SQLException erro) {
            habilitado = false;
        }

        return habilitado != false;
    }
    

    public void atualizaMedicoLogadaComBancoDeDados(String crm, Medico medico, MedicoDAO medicoDAO) {

        Medico medicoAtualizado = medicoDAO.buscaMedicoPorCrm(crm);
        medico.getPessoa().setLoginPessoa(medicoAtualizado.getPessoa().getLoginPessoa());
        medico.getPessoa().setSenhaPessoa(medicoAtualizado.getPessoa().getSenhaPessoa());
        medico.getPessoa().setTelefonePessoa(medicoAtualizado.getPessoa().getTelefonePessoa());
        medico.getPessoa().setDataCriacao(medicoAtualizado.getPessoa().getDataCriacao());
        medico.getPessoa().setDataModificacao(medicoAtualizado.getPessoa().getDataModificacao());

    }

}
