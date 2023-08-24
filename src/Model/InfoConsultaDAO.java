package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class InfoConsultaDAO {

    private List <InfoConsulta> listaInfoConsulta = new LinkedList();

    public InfoConsultaDAO() {
    }


    public InfoConsulta mostraTodasInfoConsultas() {
        for (InfoConsulta infoConsulta : listaInfoConsulta) {

            if (infoConsulta != null) {
                System.out.println(infoConsulta + "\n");
            }
        }
        return null;
    }

    public InfoConsulta buscaInfoConsultasPorMedico(Medico medico) {
        for (InfoConsulta infoConsulta : listaInfoConsulta) {

            if (infoConsulta != null && infoConsulta.getConsulta().getMedico().equals(medico)) {
                System.out.println(infoConsulta + "\n");
            }
        }
        return null;
    }
    
    public InfoConsulta buscaInfoConsultaPorId(int idInfoConsulta)
    {
        for (InfoConsulta infoConsulta : listaInfoConsulta) {
            
            if(infoConsulta != null && infoConsulta.getIdInfoConsulta() == idInfoConsulta)
            {
                return infoConsulta;
            }
        }
        return null;
    }

     public void BuscaInfoConsultaNoBancoDeDados(ConsultaDAO consultaDAO) {

        listaInfoConsulta.clear();

        String buscaInfoConsulta = "select * from infoconsulta;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstm = connection.prepareStatement(buscaInfoConsulta);
                ResultSet rs = pstm.executeQuery(buscaInfoConsulta)) {

            while (rs.next()) {

                InfoConsulta infoConsulta = new InfoConsulta();
                
                infoConsulta.setIdInfoConsulta(rs.getInt("idinfoconsulta"));
                
                int idConsulta = rs.getInt("idconsulta");
                Consulta consulta = consultaDAO.buscaConsultaPorId(idConsulta);
                
                infoConsulta.setConsulta(consulta);
                
                infoConsulta.setDescricao(rs.getString("descricaoconsulta"));
                
                Timestamp dataCriacaoInfoConsulta = rs.getTimestamp("datacriacao");
                infoConsulta.setDataCriacao(dataCriacaoInfoConsulta.toLocalDateTime());
                
                Timestamp dataModificacaoInfoConsulta = rs.getTimestamp("datamodificacao");
                
                if(dataModificacaoInfoConsulta != null)
                {
                   infoConsulta.setDataModificacao(dataModificacaoInfoConsulta.toLocalDateTime());  
                }
               
                listaInfoConsulta.add(infoConsulta);

            }

        } catch (SQLException erro) {
        }
 
     }
     
     
     public boolean atualizaDescricaoInfoConsultaNoBancoDeDados(InfoConsulta infoConsulta, String descricao,
             CalendarioSistema calendarioSistema) {

        boolean cancelado = true;

        String atualizaDescricaoInfoConsulta = "update infoconsulta set descricaoconsulta = ? where idinfoconsulta = ?";
        
        String atualizaDataAlteracaoInfoConsulta = "update infoconsulta set datamodificacao = ? where idinfoconsulta = ?";


        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaDescricaoInfoConsulta = connection.prepareStatement(atualizaDescricaoInfoConsulta);
                 PreparedStatement pstmAtualizaDataAlteracaoInfoConsulta = 
                         connection.prepareStatement(atualizaDataAlteracaoInfoConsulta)) {

               pstmAtualizaDescricaoInfoConsulta.setString(1, descricao);
               pstmAtualizaDescricaoInfoConsulta.setInt(2, infoConsulta.getIdInfoConsulta());
               
               pstmAtualizaDescricaoInfoConsulta.execute();
               
               pstmAtualizaDataAlteracaoInfoConsulta.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
               pstmAtualizaDataAlteracaoInfoConsulta.setInt(2, infoConsulta.getIdInfoConsulta());
               
               pstmAtualizaDataAlteracaoInfoConsulta.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                cancelado = false;
            }

        } catch (Exception e) {
        }

        return cancelado != false;
    }
     
}
