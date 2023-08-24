package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class PessoaDAO {

    private List<Pessoa> listaPessoa = new LinkedList();

    public PessoaDAO(CalendarioSistema calendarioSistema) {
    }

    public void mostraTodasPessoas() {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null) {
                System.out.println(pessoa + "\n");
            }
        }
    }

    public Pessoa buscaPessoaCadastrada(String login, String senha) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getLoginPessoa().equals(login)
                    && pessoa.getSenhaPessoa().equals(senha)) {
                return pessoa;
            }
        }
        return null;
    }

    public boolean verificaSePessoaExiste(String login, String cpf) {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getLoginPessoa().equals(login)
                    || pessoa != null && pessoa.getCpf().equals(cpf)) {
                return true;
            }

        }
        return false;
    }

    public Pessoa buscaPessoaQuerendoLogar(String login, String senha) {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getLoginPessoa().equals(login)
                    && pessoa.getSenhaPessoa().equals(senha)) {
                return pessoa;
            }
        }
        return null;
    }

    private boolean verificaSeCpfEstaSendoUsado(String cpf) {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaSeTelefoneEstaSendoUsado(String telefone) {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getTelefonePessoa().equals(telefone)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaSeloginEstaSendoUsado(String login) {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getLoginPessoa().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public Pessoa filtraPessoasCandidatasADonoDeFranquia() {

        String cpfDono = null;

        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getTipoUsuario().equals("DonodeFranquia")) {
                cpfDono = pessoa.getCpf();
            }
            if (pessoa != null && !pessoa.getTipoUsuario().equals("DonoDeUnidadeDeFranquia")
                    && !pessoa.getTipoUsuario().equals("Medico")
                    && !pessoa.getCpf().equals(cpfDono)
                    && !pessoa.getTipoUsuario().equals("Admnistrador")
                    && pessoa.isHabilitado() == true) {

                System.out.println(pessoa + "\n");
            }

        }

        return null;
    }

    public Pessoa buscaPessoaPorId(int idPessoa) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getId() == idPessoa) {
                return pessoa;
            }
        }
        return null;

    }

    public Pessoa buscaPessoaMedicoPorCpf(String CpfPessoa) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getCpf().equals(CpfPessoa)
                    && pessoa.getTipoUsuario().equals("Medico")) {

                return pessoa;
            }
        }
        return null;

    }

    public Pessoa buscaPessoaDonoDeFranquiaPorCpf(String CpfDonoFranquia) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getCpf().equals(CpfDonoFranquia)
                    && pessoa.getTipoUsuario().equals("DonodeFranquia")) {

                return pessoa;
            }
        }
        return null;

    }

    public Pessoa buscaPessoaDonoDeUnidadeFranquiaPorCpf(String CpfDonoUnidadeFranquia) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getCpf().equals(CpfDonoUnidadeFranquia)
                    && pessoa.getTipoUsuario().equals("DonoDeUnidadeDeFranquia")) {

                return pessoa;
            }
        }
        return null;

    }

    public Pessoa buscaPessoaAdmnistradorPorCpf(String CpfAdmnistrador) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getCpf().equals(CpfAdmnistrador)
                    && pessoa.getTipoUsuario().equals("Admnistrador")) {

                return pessoa;
            }
        }
        return null;

    }

    public Pessoa buscaPessoaPacientePorCpf(String CpfPaciente) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getCpf().equals(CpfPaciente)
                    && pessoa.getTipoUsuario().equals("Paciente")) {

                return pessoa;
            }
        }
        return null;

    }

    public Pessoa filtraPessoasCandidatasAMedico() {

        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && !pessoa.getTipoUsuario().equals("Medico")
                    && !pessoa.getTipoUsuario().equals("DonodeFranquia")
                    && !pessoa.getTipoUsuario().equals("DonoDeUnidadeDeFranquia")
                    && !pessoa.getTipoUsuario().equals("Admnistrador")
                    && pessoa.isHabilitado() == true) {
                System.out.println(pessoa + "\n");
            }
        }

        return null;
    }

    public Pessoa filtraPessoaCandidatasADonoUnidadeFranquia() {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && !pessoa.getTipoUsuario().equals("Medico")
                    && !pessoa.getTipoUsuario().equals("DonodeFranquia")
                    && !pessoa.getTipoUsuario().equals("DonoDeUnidadeDeFranquia")
                    && !pessoa.getTipoUsuario().equals("Admnistrador")
                    && pessoa.isHabilitado() == true) {
                System.out.println(pessoa + "\n");
            }
        }
        return null;
    }

    public Pessoa filtraPacientes() {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getTipoUsuario().equals("Paciente")
                    && pessoa.isHabilitado() == true) {
                System.out.println(pessoa + "\n");
            }
        }
        return null;
    }

    public Pessoa filtraMedicos() {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getTipoUsuario().equals("Medico")
                    && pessoa.isHabilitado() == true) {
                System.out.println(pessoa + "\n");
            }
        }
        return null;
    }

    public boolean excluirPaciente(Pessoa pessoa, CalendarioSistema calendarioSistema) {
        if (pessoa != null
                && pessoa.getTipoUsuario().equals("Paciente")
                && pessoa.isHabilitado() == true) {
            pessoa.setHabilitado(false);
            pessoa.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }

    public void filtraPacientesExcluidos() {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null
                    && pessoa.getTipoUsuario().equals("Paciente")
                    && pessoa.isHabilitado() == false) {
                System.out.println(pessoa + "\n");
            }
        }
    }

    public Pessoa buscaPessoaExcluidaPorId(int idPessoaExcluida) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getId() == idPessoaExcluida
                    && pessoa.isHabilitado() == false) {
                return pessoa;
            }
        }
        return null;

    }

    public boolean ReverterExclusaoPaciente(Pessoa pessoa, CalendarioSistema calendarioSistema) {
        if (pessoa != null
                && pessoa.getTipoUsuario().equals("Paciente")
                && pessoa.isHabilitado() == false) {
            pessoa.setHabilitado(true);
            pessoa.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }

    public boolean inserePessoaNoBancoDeDados(Pessoa pessoa) {

        boolean adicionado = true;

        String inserePessoa = "insert into pessoa (nome,enderecopessoa,cpf) \n"
                + "values (?,?,?)";

        String insereTipoUsuario = "insert into tipousuario (cpfpessoa,logintipousuario,senhatipousuario,"
                + "tipousuario, telefonepessoa, datacriacao, habilitado) \n"
                + "values (?,?,?,?,?,?,?)";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmInserePessoa = connection.prepareStatement(inserePessoa); PreparedStatement pstmInsereTipoUsuario = connection.prepareStatement(insereTipoUsuario)) {

                pstmInserePessoa.setString(1, pessoa.getNomePessoa());
                pstmInserePessoa.setString(2, pessoa.getEnderecoPessoa());
                pstmInserePessoa.setString(3, pessoa.getCpf());

                pstmInserePessoa.execute();

                pstmInsereTipoUsuario.setString(1, pessoa.getCpf());
                pstmInsereTipoUsuario.setString(2, pessoa.getLoginPessoa());
                pstmInsereTipoUsuario.setString(3, pessoa.getSenhaPessoa());
                pstmInsereTipoUsuario.setString(4, pessoa.getTipoUsuario());
                pstmInsereTipoUsuario.setString(5, pessoa.getTelefonePessoa());
                pstmInsereTipoUsuario.setTimestamp(6, Timestamp.valueOf(pessoa.getDataCriacao()));
                pstmInsereTipoUsuario.setBoolean(7, pessoa.isHabilitado());

                pstmInsereTipoUsuario.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                adicionado = false;

            }

        } catch (SQLException erro) {

        }

        return adicionado != false;

    }

    public void BuscaPessoaNoBancoDeDados() {

        listaPessoa.clear();

        String buscaPessoa = "select p.*, tp.*\n"
                + "from pessoa p inner join tipousuario tp\n"
                + "on p.cpf = tp.cpfpessoa";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados(); PreparedStatement pstm = connection.prepareStatement(buscaPessoa); ResultSet rs = pstm.executeQuery(buscaPessoa)) {

            while (rs.next()) {

                Pessoa pessoa = new Pessoa();

                pessoa.setNomePessoa(rs.getString("nome"));
                pessoa.setEnderecoPessoa(rs.getString("enderecopessoa"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setIdPessoa(rs.getInt("idpessoa"));
                pessoa.setLoginPessoa(rs.getString("logintipousuario"));
                pessoa.setSenhaPessoa(rs.getString("senhatipousuario"));
                pessoa.setTipoUsuario(rs.getString("tipousuario"));
                pessoa.setTelefonePessoa(rs.getString("telefonepessoa"));

                Timestamp dataCriacao = rs.getTimestamp("datacriacao");
                pessoa.setDataCriacao(dataCriacao.toLocalDateTime());

                Timestamp dataModificacao = rs.getTimestamp("datamodificacao");
                if (dataModificacao != null) {
                    pessoa.setDataModificacao(dataModificacao.toLocalDateTime());
                }

                pessoa.setHabilitado(rs.getBoolean("habilitado"));

                listaPessoa.add(pessoa);

            }

        } catch (SQLException erro) {
        }

    }

    public boolean AtualizaNomePessoaNoBancoDeDados(String novoNome, Pessoa pessoa,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaNomePessoa = "update pessoa set nome = ?  where cpf = ?";

        String atualizaDataAlteracaoPessoa = "update tipousuario set datamodificacao = ? where cpfpessoa = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaNomePessoa = connection.prepareStatement(atualizaNomePessoa); PreparedStatement pstmAtualizaDataAlteracaoPessoa = connection.prepareStatement(atualizaDataAlteracaoPessoa)) {

                pstmAtualizaNomePessoa.setString(1, novoNome);
                pstmAtualizaNomePessoa.setString(2, pessoa.getCpf());

                pstmAtualizaNomePessoa.execute();

                pstmAtualizaDataAlteracaoPessoa.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                pstmAtualizaDataAlteracaoPessoa.setString(2, pessoa.getCpf());

                pstmAtualizaDataAlteracaoPessoa.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                atualizado = false;
            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

    public boolean AtualizaCpfPessoaNoBancoDeDados(String novoCpf, Pessoa pessoa,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaCpfPessoa = "update pessoa set cpf = ? where cpf = ?";

        String atualizaDataAlteracaoPessoa = "update tipousuario set datamodificacao = ? where cpfpessoa = ?";

        if (!verificaSeCpfEstaSendoUsado(novoCpf) == true) {

            try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

                connection.setAutoCommit(false);

                try (PreparedStatement pstmAtualizaCpfPessoa = connection.prepareStatement(atualizaCpfPessoa); PreparedStatement pstmAtualizaDataAlteracaoPessoa = connection.prepareStatement(atualizaDataAlteracaoPessoa)) {

                    pstmAtualizaCpfPessoa.setString(1, novoCpf);
                    pstmAtualizaCpfPessoa.setString(2, pessoa.getCpf());

                    pstmAtualizaCpfPessoa.execute();

                    pstmAtualizaDataAlteracaoPessoa.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                    pstmAtualizaDataAlteracaoPessoa.setString(2, novoCpf);

                    pstmAtualizaDataAlteracaoPessoa.execute();

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

    public boolean AtualizaEnderecoPessoaNoBancoDeDados(String novoeEndereco, Pessoa pessoa,
            CalendarioSistema calendarioSistema) {

        System.out.println("\nO CPF da pessoa logada he: " + pessoa.getCpf());

        boolean atualizado = true;

        String atualizaEnderecoPessoa = "update pessoa set enderecopessoa = ? where cpf = ?";

        String atualizaDataAlteracaoPessoa = "update tipousuario set datamodificacao = ? where cpfpessoa = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaEnderecoPessoa = connection.prepareStatement(atualizaEnderecoPessoa); PreparedStatement pstmAtualizaDataAlteracaoPessoa = connection.prepareStatement(atualizaDataAlteracaoPessoa)) {

                pstmAtualizaEnderecoPessoa.setString(1, novoeEndereco);
                pstmAtualizaEnderecoPessoa.setString(2, pessoa.getCpf());

                pstmAtualizaEnderecoPessoa.execute();

                pstmAtualizaDataAlteracaoPessoa.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                pstmAtualizaDataAlteracaoPessoa.setString(2, pessoa.getCpf());
                pstmAtualizaDataAlteracaoPessoa.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                atualizado = false;
            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

    public boolean AtualizaTelefonePessoaNoBancoDeDados(String novoTelefonePessoa,
            Pessoa pessoa, CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaTelefonePessoa = "update tipousuario set telefonepessoa = ? where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoa = "update tipousuario set datamodificacao = ? where cpfpessoa = ?";

        if (!verificaSeTelefoneEstaSendoUsado(novoTelefonePessoa) == true) {

            try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

                connection.setAutoCommit(false);

                try (PreparedStatement pstmAtualizaTelefonePessoa = connection.prepareStatement(atualizaTelefonePessoa); PreparedStatement pstmAtualizaDataAlteracaoPessoa = connection.prepareStatement(atualizaDataAlteracaoPessoa)) {

                    pstmAtualizaTelefonePessoa.setString(1, novoTelefonePessoa);
                    pstmAtualizaTelefonePessoa.setString(2, pessoa.getCpf());
                    pstmAtualizaTelefonePessoa.setString(3, pessoa.getTipoUsuario());

                    pstmAtualizaTelefonePessoa.execute();

                    pstmAtualizaDataAlteracaoPessoa.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                    pstmAtualizaDataAlteracaoPessoa.setString(2, pessoa.getCpf());
                    pstmAtualizaDataAlteracaoPessoa.execute();

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

    public boolean AtualizaloginPessoaNoBancoDeDados(String novoLoginPessoa, Pessoa pessoa,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaLoginPessoa = "update tipousuario set logintipousuario = ? where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoa = "update tipousuario set datamodificacao = ? where cpfpessoa = ?";

        if (!verificaSeloginEstaSendoUsado(novoLoginPessoa) == true) {

            try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

                connection.setAutoCommit(false);

                try (PreparedStatement pstmAtualizaLoginPessoa = connection.prepareStatement(atualizaLoginPessoa); PreparedStatement pstmAtualizaDataAlteracaoPessoa = connection.prepareStatement(atualizaDataAlteracaoPessoa)) {

                    pstmAtualizaLoginPessoa.setString(1, novoLoginPessoa);
                    pstmAtualizaLoginPessoa.setString(2, pessoa.getCpf());
                    pstmAtualizaLoginPessoa.setString(3, pessoa.getTipoUsuario());

                    pstmAtualizaLoginPessoa.execute();

                    pstmAtualizaDataAlteracaoPessoa.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                    pstmAtualizaDataAlteracaoPessoa.setString(2, pessoa.getCpf());
                    pstmAtualizaDataAlteracaoPessoa.execute();

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

    public boolean AtualizaSenhaPessoaNoBancoDeDados(String novaSenha, Pessoa pessoa,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaSenhaPessoa = "update tipousuario set senhatipousuario = ? "
                + "where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoa = "update tipousuario set datamodificacao = ?"
                + " where cpfpessoa = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaSenhaPessoa = 
                    connection.prepareStatement(atualizaSenhaPessoa); 
                    PreparedStatement pstmAtualizaDataAlteracaoPessoa 
                            = connection.prepareStatement(atualizaDataAlteracaoPessoa)) {

                pstmAtualizaSenhaPessoa.setString(1, novaSenha);
                pstmAtualizaSenhaPessoa.setString(2, pessoa.getCpf());
                pstmAtualizaSenhaPessoa.setString(3, pessoa.getTipoUsuario());

                pstmAtualizaSenhaPessoa.execute();

                pstmAtualizaDataAlteracaoPessoa.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                pstmAtualizaDataAlteracaoPessoa.setString(2, pessoa.getCpf());
                pstmAtualizaDataAlteracaoPessoa.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                atualizado = false;
                
            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }
    
    public boolean excluirPacienteNoBancoDeDados(Pessoa pessoa, 
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
    
     public boolean habilitaPacienteNoBancoDeDados(Pessoa pessoa, 
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


    public void atualizaPessoaLogadaComBancoDeDados(String novoCpf, Pessoa pessoa) {

        Pessoa pessoaAtualizada = buscaPessoaPacientePorCpf(novoCpf);
        pessoa.setIdPessoa(pessoaAtualizada.getId());
        pessoa.setNomePessoa(pessoaAtualizada.getNomePessoa());
        pessoa.setCpf(pessoaAtualizada.getCpf());
        pessoa.setEnderecoPessoa(pessoaAtualizada.getEnderecoPessoa());
        pessoa.setTelefonePessoa(pessoaAtualizada.getTelefonePessoa());
        pessoa.setLoginPessoa(pessoaAtualizada.getLoginPessoa());
        pessoa.setSenhaPessoa(pessoaAtualizada.getSenhaPessoa());
        pessoa.setTipoUsuario(pessoaAtualizada.getTipoUsuario());
        pessoa.setHabilitado(true);
        pessoa.setDataCriacao(pessoaAtualizada.getDataCriacao());
        pessoa.setDataModificacao(pessoaAtualizada.getDataModificacao());

    }

}
