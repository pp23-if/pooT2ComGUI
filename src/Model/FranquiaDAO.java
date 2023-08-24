package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class FranquiaDAO {

    private List<Franquia> listaFranquia = new LinkedList();

    public FranquiaDAO(PessoaDAO pessoaDAO, CalendarioSistema calendarioSistema) {
    }

    public Franquia buscaFranquia(Franquia f) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.equals(f)) {
                return franquia;
            }
        }
        return null;
    }

    public Franquia buscaFranquiaAtravesDaPessoaVinculada(Pessoa pessoaLogada) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getPessoa().equals(pessoaLogada)) {
                return franquia;
            }
        }
        return null;
    }

    public void mostraTodasFranquias() {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null) {
                System.out.println(franquia + "\n");
            }
        }
    }

    public boolean verificaSeFranquiaExiste(String nomeFranquia, String cnpj) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getNomeFranquia().equals(nomeFranquia.toUpperCase())
                    || franquia != null && franquia.getNomeFranquia().equals(nomeFranquia.toLowerCase())
                    || franquia != null && franquia.getCnpj().equals(cnpj)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaDonosDeFranquia(Pessoa p) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getPessoa().getCpf().equals(p.getCpf())) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaSeNomeFranquiaEstaSendoUsado(String novoNomeFranquia) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getNomeFranquia().equals(novoNomeFranquia.toUpperCase())
                    || franquia != null && franquia.getNomeFranquia().equals(novoNomeFranquia.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaSeLoginDonoFranquiaEstaSendoUsado(String novoLoginDonoFranquia) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getPessoa().getLoginPessoa().equals(novoLoginDonoFranquia)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaSeTelefoneDonoFranquiaEstaSendoUsado(String novoTelefoneDonoFranquia) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getPessoa().getTelefonePessoa().equals(novoTelefoneDonoFranquia)) {
                return true;
            }
        }
        return false;
    }

    public Franquia buscaFranquiaPorCnpj(String cnpjFranquia) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getCnpj().equals(cnpjFranquia)) {
                return franquia;
            }
        }
        return null;
    }

    public Franquia buscaFranquiaPorId(int idFranquia) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getIdFranquia() == idFranquia) {
                return franquia;
            }
        }
        return null;
    }

    public boolean insereFranquiaNoBancoDeDados(Pessoa pessoa, Franquia franquia) {

        boolean adicionado = true;

        String inserePessoaDonoDeFranquia = "insert into tipousuario (cpfpessoa,logintipousuario,senhatipousuario,"
                + "tipousuario, telefonepessoa, datacriacao, habilitado) \n"
                + "values (?,?,?,?,?,?,?)";

        String insereFranquia = "insert into franquia (nomefranquia, cnpj, cidade, endereco, cpfdono, datacriacao) \n"
                + "values (?,?,?,?,?,?)";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmInsereDonoDeFranquia = connection.prepareStatement(inserePessoaDonoDeFranquia); PreparedStatement pstmInsereFranquia = connection.prepareStatement(insereFranquia)) {

                pstmInsereDonoDeFranquia.setString(1, pessoa.getCpf());
                pstmInsereDonoDeFranquia.setString(2, pessoa.getLoginPessoa());
                pstmInsereDonoDeFranquia.setString(3, pessoa.getSenhaPessoa());
                pstmInsereDonoDeFranquia.setString(4, pessoa.getTipoUsuario());
                pstmInsereDonoDeFranquia.setString(5, pessoa.getTelefonePessoa());
                pstmInsereDonoDeFranquia.setTimestamp(6, Timestamp.valueOf(pessoa.getDataCriacao()));
                pstmInsereDonoDeFranquia.setBoolean(7, pessoa.isHabilitado());

                pstmInsereDonoDeFranquia.execute();

                pstmInsereFranquia.setString(1, franquia.getNomeFranquia());
                pstmInsereFranquia.setString(2, franquia.getCnpj());
                pstmInsereFranquia.setString(3, franquia.getCidade());
                pstmInsereFranquia.setString(4, franquia.getEnderecoFranquia());
                pstmInsereFranquia.setString(5, pessoa.getCpf());
                pstmInsereFranquia.setTimestamp(6, Timestamp.valueOf(franquia.getDataCriacao()));

                pstmInsereFranquia.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                adicionado = false;

            }

        } catch (SQLException erro) {

        }

        return adicionado != false;

    }

    public void BuscaFranquiaNoBancoDeDados(PessoaDAO pessoaDAO) {

        listaFranquia.clear();

        String buscaFranquia = "select idfranquia, nomefranquia, cnpj, cidade, endereco, "
                + "cpfdono, datacriacao, datamodificacao"
                + " from franquia;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados(); 
                PreparedStatement pstm = connection.prepareStatement(buscaFranquia); 
                ResultSet rs = pstm.executeQuery(buscaFranquia)) {

            while (rs.next()) {

                Franquia franquia = new Franquia();

                String cpfDonoFranquia = rs.getString("cpfdono");
                Pessoa pessoaDonoFranquia = pessoaDAO.buscaPessoaDonoDeFranquiaPorCpf(cpfDonoFranquia);
                franquia.setPessoa(pessoaDonoFranquia);

                franquia.setIdFranquia(rs.getInt("idfranquia"));
                franquia.setNomeFranquia(rs.getString("nomefranquia"));
                franquia.setCnpj(rs.getString("cnpj"));
                franquia.setCidade(rs.getString("cidade"));
                franquia.setEnderecoFranquia(rs.getString("endereco"));

                Timestamp dataCriacaoFranquia = rs.getTimestamp("datacriacao");
                franquia.setDataCriacao(dataCriacaoFranquia.toLocalDateTime());

                Timestamp dataModificacaoFranquia = rs.getTimestamp("datamodificacao");
                if (dataModificacaoFranquia != null) {
                    franquia.setDataModificacao(dataModificacaoFranquia.toLocalDateTime());
                }

                listaFranquia.add(franquia);

            }

        } catch (SQLException erro) {
        }

    }

    public boolean AtualizaNomeFranquiaNoBancoDeDados(String novoNomeFranquia, Franquia franquia,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaNomeFranquia = "update franquia set nomefranquia = ? where cnpj = ?";

        String atualizaDataAlteracaoFranquia = "update franquia set datamodificacao = ? where cnpj = ?";

        if (!verificaSeNomeFranquiaEstaSendoUsado(novoNomeFranquia) == true) {

            try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

                connection.setAutoCommit(false);

                try (PreparedStatement pstmAtualizaNomeFranquia = connection.prepareStatement(atualizaNomeFranquia); 
                      PreparedStatement pstmAtualizaDataAlteracaoFranquia = 
                              connection.prepareStatement(atualizaDataAlteracaoFranquia)) {

                    pstmAtualizaNomeFranquia.setString(1, novoNomeFranquia.toUpperCase());
                    pstmAtualizaNomeFranquia.setString(2, franquia.getCnpj());

                    pstmAtualizaNomeFranquia.execute();

                    pstmAtualizaDataAlteracaoFranquia.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                    pstmAtualizaDataAlteracaoFranquia.setString(2, franquia.getCnpj());

                    pstmAtualizaDataAlteracaoFranquia.execute();

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

    public boolean AtualizaCidadeFranquiaNoBancoDeDados(String novaCidadeFranquia, Franquia franquia,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaCidadeFranquia = "update franquia set cidade = ? where cnpj = ?";

        String atualizaDataAlteracaoFranquia = "update franquia set datamodificacao = ? where cnpj = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaCidadeFranquia = connection.prepareStatement(atualizaCidadeFranquia); PreparedStatement pstmAtualizaDataAlteracaoFranquia = connection.prepareStatement(atualizaDataAlteracaoFranquia)) {

                pstmAtualizaCidadeFranquia.setString(1, novaCidadeFranquia);
                pstmAtualizaCidadeFranquia.setString(2, franquia.getCnpj());

                pstmAtualizaCidadeFranquia.execute();

                pstmAtualizaDataAlteracaoFranquia.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                pstmAtualizaDataAlteracaoFranquia.setString(2, franquia.getCnpj());

                pstmAtualizaDataAlteracaoFranquia.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                atualizado = false;
            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

    public boolean AtualizaEnderecoFranquiaNoBancoDeDados(String novoEnderecoFranquia, Franquia franquia,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaEnderecoFranquia = "update franquia set endereco = ? where cnpj = ?";

        String atualizaDataAlteracaoFranquia = "update franquia set datamodificacao = ? where cnpj = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaEnderecoFranquia = connection.prepareStatement(atualizaEnderecoFranquia); PreparedStatement pstmAtualizaDataAlteracaoFranquia = connection.prepareStatement(atualizaDataAlteracaoFranquia)) {

                pstmAtualizaEnderecoFranquia.setString(1, novoEnderecoFranquia);
                pstmAtualizaEnderecoFranquia.setString(2, franquia.getCnpj());

                pstmAtualizaEnderecoFranquia.execute();

                pstmAtualizaDataAlteracaoFranquia.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                pstmAtualizaDataAlteracaoFranquia.setString(2, franquia.getCnpj());

                pstmAtualizaDataAlteracaoFranquia.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                atualizado = false;
            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

    public boolean AtualizaLoginDonoFranquiaNoBancoDeDados(String novoLoginDonoFranquia, Franquia franquia,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaLoginDonoFranquia = "update tipousuario set logintipousuario = ? where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoaDonoFranquia = "update tipousuario set datamodificacao = ? "
                + "where cpfpessoa = ? and tipousuario = ?";

        if (!verificaSeLoginDonoFranquiaEstaSendoUsado(novoLoginDonoFranquia) == true) {

            try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

                connection.setAutoCommit(false);

                try (PreparedStatement pstmAtualizaLoginDonoFranquia = connection.prepareStatement(atualizaLoginDonoFranquia); PreparedStatement pstmAtualizaDataAlteracaoPessoaDonoFranquia
                        = connection.prepareStatement(atualizaDataAlteracaoPessoaDonoFranquia)) {

                    pstmAtualizaLoginDonoFranquia.setString(1, novoLoginDonoFranquia);
                    pstmAtualizaLoginDonoFranquia.setString(2, franquia.getPessoa().getCpf());
                    pstmAtualizaLoginDonoFranquia.setString(3, franquia.getPessoa().getTipoUsuario());

                    pstmAtualizaLoginDonoFranquia.execute();

                    pstmAtualizaDataAlteracaoPessoaDonoFranquia.setTimestamp(1,
                            Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));

                    pstmAtualizaDataAlteracaoPessoaDonoFranquia.setString(2, franquia.getPessoa().getCpf());
                    pstmAtualizaDataAlteracaoPessoaDonoFranquia.setString(3, franquia.getPessoa().getTipoUsuario());

                    pstmAtualizaDataAlteracaoPessoaDonoFranquia.execute();

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

    public boolean AtualizaSenhaDonoFranquiaNoBancoDeDados(String novaSenhaDonoFranquia, Franquia franquia,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaSenhaDonoFranquia = "update tipousuario set senhatipousuario = ? where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoaDonoFranquia = "update tipousuario set datamodificacao = ? "
                + "where cpfpessoa = ? and tipousuario = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaSenhaDonoFranquia = connection.prepareStatement(atualizaSenhaDonoFranquia); PreparedStatement pstmAtualizaDataAlteracaoPessoaDonoFranquia
                    = connection.prepareStatement(atualizaDataAlteracaoPessoaDonoFranquia)) {

                pstmAtualizaSenhaDonoFranquia.setString(1, novaSenhaDonoFranquia);
                pstmAtualizaSenhaDonoFranquia.setString(2, franquia.getPessoa().getCpf());
                pstmAtualizaSenhaDonoFranquia.setString(3, franquia.getPessoa().getTipoUsuario());

                pstmAtualizaSenhaDonoFranquia.execute();

                pstmAtualizaDataAlteracaoPessoaDonoFranquia.setTimestamp(1,
                        Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));

                pstmAtualizaDataAlteracaoPessoaDonoFranquia.setString(2, franquia.getPessoa().getCpf());
                pstmAtualizaDataAlteracaoPessoaDonoFranquia.setString(3, franquia.getPessoa().getTipoUsuario());

                pstmAtualizaDataAlteracaoPessoaDonoFranquia.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                atualizado = false;
            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

    public boolean AtualizaTelefoneDonoFranquiaNoBancoDeDados(String novoTelefoneDonoFranquia, Franquia franquia,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaLoginDonoFranquia = "update tipousuario set telefonepessoa = ? where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoaDonoFranquia = "update tipousuario set datamodificacao = ? "
                + "where cpfpessoa = ? and tipousuario = ?";

        if (!verificaSeTelefoneDonoFranquiaEstaSendoUsado(novoTelefoneDonoFranquia) == true) {

            try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

                connection.setAutoCommit(false);

                try (PreparedStatement pstmAtualizaLoginDonoFranquia = connection.prepareStatement(atualizaLoginDonoFranquia); 
                        PreparedStatement pstmAtualizaDataAlteracaoPessoaDonoFranquia
                        = connection.prepareStatement(atualizaDataAlteracaoPessoaDonoFranquia)) {

                    pstmAtualizaLoginDonoFranquia.setString(1, novoTelefoneDonoFranquia);
                    pstmAtualizaLoginDonoFranquia.setString(2, franquia.getPessoa().getCpf());
                    pstmAtualizaLoginDonoFranquia.setString(3, franquia.getPessoa().getTipoUsuario());

                    pstmAtualizaLoginDonoFranquia.execute();

                    pstmAtualizaDataAlteracaoPessoaDonoFranquia.setTimestamp(1,
                            Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));

                    pstmAtualizaDataAlteracaoPessoaDonoFranquia.setString(2, franquia.getPessoa().getCpf());
                    pstmAtualizaDataAlteracaoPessoaDonoFranquia.setString(3, franquia.getPessoa().getTipoUsuario());

                    pstmAtualizaDataAlteracaoPessoaDonoFranquia.execute();

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

    public void atualizaFranquiaLogadaComBancoDeDados(String cnpj, Franquia franquia) {

        Franquia franquiaAtualizada = buscaFranquiaPorCnpj(cnpj);
        franquia.setNomeFranquia(franquiaAtualizada.getNomeFranquia());
        franquia.setCidade(franquiaAtualizada.getCidade());
        franquia.setEnderecoFranquia(franquiaAtualizada.getEnderecoFranquia());
        franquia.setDataCriacao(franquiaAtualizada.getDataCriacao());
        franquia.setDataModificacao(franquiaAtualizada.getDataModificacao());

        franquia.getPessoa().setLoginPessoa(franquiaAtualizada.getPessoa().getLoginPessoa());
        franquia.getPessoa().setSenhaPessoa(franquiaAtualizada.getPessoa().getSenhaPessoa());
        franquia.getPessoa().setTelefonePessoa(franquiaAtualizada.getPessoa().getTelefonePessoa());
        franquia.getPessoa().setDataCriacao(franquiaAtualizada.getPessoa().getDataCriacao());
        franquia.getPessoa().setDataModificacao(franquiaAtualizada.getPessoa().getDataModificacao());

    }

}
