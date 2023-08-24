package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class AdmnistradorDAO {

    private List<Admnistrador> listaAdmnistrador = new LinkedList();

    public AdmnistradorDAO(PessoaDAO pessoaDAO, FranquiaDAO franquiaDAO, CalendarioSistema calendarioSistema) {
    }


    public Admnistrador buscaTodosAdmnistradores() {
        for (Admnistrador admnistrador : listaAdmnistrador) {
            if (admnistrador != null) {
                System.out.println(admnistrador + "\n");
            }
        }
        return null;
    }

    public Admnistrador buscaAdmnistradorAtravesPessoaVinculada(Pessoa pessoa) {
        for (Admnistrador admnistrador : listaAdmnistrador) {
            if (admnistrador != null && admnistrador.getPessoa().equals(pessoa)) {
                return admnistrador;
            }
        }
        return null;
    }

    public boolean verificaSeFranquiaPossuiAdmnistrador(Franquia franquia) {
        for (Admnistrador admnistrador : listaAdmnistrador) {

            if (admnistrador != null && admnistrador.getFranquia().equals(franquia)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaSePessoaEhAdmnistrador(Pessoa pessoa) {
        for (Admnistrador admnistrador : listaAdmnistrador) {

            if (admnistrador != null
                    && admnistrador.getPessoa().getCpf().equals(pessoa.getCpf())) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaSeLoginAdmnistradorEstaEmUso(String loginAdmnistrador) {
        for (Admnistrador admnistrador : listaAdmnistrador) {

            if (admnistrador != null && admnistrador.getPessoa().getLoginPessoa().equals(loginAdmnistrador)) {
                return true;
            }
        }
        return false;
    }

    public void BuscaAdmnistradorNoBancoDeDados(PessoaDAO pessoaDAO, FranquiaDAO franquiaDAO) {

        listaAdmnistrador.clear();

        String buscaAdmnistrador = "select * from admnistrador;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados(); PreparedStatement pstm = connection.prepareStatement(buscaAdmnistrador); ResultSet rs = pstm.executeQuery(buscaAdmnistrador)) {

            while (rs.next()) {

                Admnistrador admnistrador = new Admnistrador();

                admnistrador.setIdAdmnistrador(rs.getInt("idadmnistrador"));

                String cpfAdmnistrador = rs.getString("cpfpessoa");
                Pessoa pessoaAdmnistrador = pessoaDAO.buscaPessoaAdmnistradorPorCpf(cpfAdmnistrador);

                String cnpjFranquia = rs.getString("cnpjfranquia");
                Franquia franquia = franquiaDAO.buscaFranquiaPorCnpj(cnpjFranquia);

                admnistrador.setPessoa(pessoaAdmnistrador);
                admnistrador.setFranquia(franquia);

                Timestamp dataCriacaoAdmnistrador = rs.getTimestamp("datacriacao");
                admnistrador.setDataCriacao(dataCriacaoAdmnistrador.toLocalDateTime());

                Timestamp dataModficacaoAdmnistrador = rs.getTimestamp("datamodificacao");

                if (dataModficacaoAdmnistrador != null) {
                    admnistrador.setDataModificacao(dataModficacaoAdmnistrador.toLocalDateTime());
                }

                listaAdmnistrador.add(admnistrador);

            }

        } catch (SQLException erro) {
        }

    }

    public boolean insereAdmnistradorNoBancoDeDados(Pessoa pessoa, Admnistrador admnistrador) {

        boolean inserido = true;

        String inserePessoaAdmnistrador = "insert into tipousuario (cpfpessoa, "
                + "logintipousuario, senhatipousuario, tipousuario,"
                + "telefonepessoa, datacriacao, habilitado) values (?,?,?,?,?,?,?)";

        String insereAdmnistrador = "insert into admnistrador (cpfpessoa, cnpjfranquia, "
                + "datacriacao) values (?,?,?)";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmInserePessoaAdmnistrador
                    = connection.prepareStatement(inserePessoaAdmnistrador); 
                    PreparedStatement pstmInsereAdmnistrador
                    = connection.prepareStatement(insereAdmnistrador)) {

                pstmInserePessoaAdmnistrador.setString(1, pessoa.getCpf());
                pstmInserePessoaAdmnistrador.setString(2, pessoa.getLoginPessoa());
                pstmInserePessoaAdmnistrador.setString(3, pessoa.getSenhaPessoa());
                pstmInserePessoaAdmnistrador.setString(4, pessoa.getTipoUsuario());
                pstmInserePessoaAdmnistrador.setString(5, pessoa.getTelefonePessoa());
                pstmInserePessoaAdmnistrador.setTimestamp(6, Timestamp.valueOf(pessoa.getDataCriacao()));
                pstmInserePessoaAdmnistrador.setBoolean(7, pessoa.isHabilitado());

                pstmInserePessoaAdmnistrador.execute();
                
                pstmInsereAdmnistrador.setString(1, pessoa.getCpf());
                pstmInsereAdmnistrador.setString(2, admnistrador.getFranquia().getCnpj());
                pstmInsereAdmnistrador.setTimestamp(3, Timestamp.valueOf(admnistrador.getDataCriacao()));
                
                pstmInsereAdmnistrador.execute();
                
                connection.commit();

            } catch (SQLException erro) {
                connection.rollback();
                inserido = false;
            }

        } catch (SQLException erro) {

            inserido = false;
        }
        return inserido != false;

    }

}
