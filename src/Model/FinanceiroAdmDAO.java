package Model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class FinanceiroAdmDAO {

    private List<FinanceiroAdm> listaFinanceiroAdm = new LinkedList();

    public FinanceiroAdmDAO() {
    }

    public FinanceiroAdm mostraTodosMovimentosFinanceiros() {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null) {
                System.out.println(financeiroAdm + "\n");
            }
        }
        return null;
    }

    public FinanceiroAdm buscaMovimentacoesFinanceirasPorFranquia(Franquia franquia) {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null && financeiroAdm.getUnidadeFranquia().getFranquia().equals(franquia)) {
                System.out.println(financeiroAdm + "\n");
            }
        }
        return null;
    }

    public void geraMovimentacaoFinanceiraPagamentosFranquia(UnidadeFranquia unidadeFranquia,
            double valorPagamento, CalendarioSistema calendarioSistema) {
        FinanceiroAdm saidaPagamentoFranquia = new FinanceiroAdm("Saida", valorPagamento, unidadeFranquia,
                "PagamentoFranquia", calendarioSistema.getDataHoraSistema());
        //adicionaFinanceiroAdm(saidaPagamentoFranquia);

        inserePagamentoAvulsoEPagamentoFranquiaNoBancoDeDados(saidaPagamentoFranquia);
    }

    public boolean verificaPagamentoUnidade(CalendarioSistema calendarioSistema, UnidadeFranquia unidadeFranquia) {

        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null
                    && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)
                    && financeiroAdm.getDescritivoMovimento().equals("PagamentoFranquia")
                    && financeiroAdm.getDataCriacao().getMonthValue() == calendarioSistema.getDataHoraSistema().getMonthValue()) {
                return true;
            }
        }
        return false;
    }

    public double calculaRendaBruta(CalendarioSistema calendarioSistema, UnidadeFranquia unidadeFranquia) {

        double valorTotalConsultas = 0;
        double valorTotalprocedimentos = 0;
        double valorTotalEntradas;

        int mesSitemaComparavel = calendarioSistema.getDiaDoSistema().minusDays(1).getMonthValue();

        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null
                    && financeiroAdm.getDescritivoMovimento().equals("Consulta")
                    && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)
                    && financeiroAdm.getDataCriacao().getMonthValue() == mesSitemaComparavel) {
                valorTotalConsultas += financeiroAdm.getValor();
            } else if (financeiroAdm != null
                    && financeiroAdm.getDescritivoMovimento().equals("Procedimento")
                    && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)
                    && financeiroAdm.getDataCriacao().getMonthValue() == mesSitemaComparavel) {
                valorTotalprocedimentos += financeiroAdm.getValor();
            }

        }
        valorTotalEntradas = valorTotalConsultas + valorTotalprocedimentos;

        return valorTotalEntradas;

    }

    public double calculaParteValorAdmnistradora(double rendaBruta, UnidadeFranquia unidadeFranquia,
            CalendarioSistema calendarioSistema) {
        double valorAdministradora;

        valorAdministradora = (rendaBruta * 0.05) + 1000;

        geraMovimentacaoFinanceiraPagamentosFranquia(unidadeFranquia, valorAdministradora, calendarioSistema);

        return valorAdministradora;
    }

    public double calculaRendaLiquida(double rendaBruta, double parteAdministradora) {
        double rendaLiquida = rendaBruta - parteAdministradora;

        return rendaLiquida;
    }

    public void geraRelatorioEntradaSaidaFranquia(Franquia franquia) {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null && financeiroAdm.getUnidadeFranquia().getFranquia().equals(franquia)) {
                System.out.println(financeiroAdm + "\n");
            }
        }
    }

    public void geraRelatorioEntradaSaidaFranquiaMes(Franquia franquia, int numeroMes) {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null
                    && financeiroAdm.getUnidadeFranquia().getFranquia().equals(franquia)
                    && financeiroAdm.getDataCriacao().getMonthValue() == numeroMes) {
                System.out.println(financeiroAdm + "\n");
            }
        }
    }

    public void geraRelatorioEntradaSaidaUnidadeFranquia(UnidadeFranquia unidadeFranquia) {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)) {
                System.out.println(financeiroAdm + "\n");
            }
        }
    }

    public void geraRelatorioEntradaSaidaUnidadeFranquiaMes(UnidadeFranquia unidadeFranquia, int numeroMes) {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null
                    && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)
                    && financeiroAdm.getDataCriacao().getMonthValue() == numeroMes) {
                System.out.println(financeiroAdm + "\n");
            }
        }
    }

    public void buscaFinanceiroADMNoBancoDeDados(UnidadeFranquiaDAO unidadeFranquiaDAO) {

        listaFinanceiroAdm.clear();

        String buscaFinanceiroADM = "select * from financeiroadm";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados(); PreparedStatement pstm = connection.prepareStatement(buscaFinanceiroADM); ResultSet rs = pstm.executeQuery(buscaFinanceiroADM)) {

            while (rs.next()) {

                FinanceiroAdm financeiroAdm = new FinanceiroAdm();

                financeiroAdm.setIdFinanceiroAdm(rs.getInt("idfinanceiroadm"));

                int idUnidadeFranquia = rs.getInt("idunidadefranquia");

                UnidadeFranquia unidadeFranquia = unidadeFranquiaDAO.buscaUnidadeFranquiaPorId(idUnidadeFranquia);

                financeiroAdm.setUnidadeFranquia(unidadeFranquia);

                financeiroAdm.setTipoMovimento(rs.getString("tipomovimento"));

                financeiroAdm.setValor(rs.getDouble("valorfinanceiroadm"));

                financeiroAdm.setDescritivoMovimento(rs.getString("descritivomovimento"));

                Timestamp dataCriacaoFinanceiroADM = rs.getTimestamp("datacriacao");
                financeiroAdm.setDataCriacao(dataCriacaoFinanceiroADM.toLocalDateTime());

                Timestamp dataModificaoFinamceiroADM = rs.getTimestamp("datamodificacao");

                if (dataModificaoFinamceiroADM != null) {

                    financeiroAdm.setDataModificacao(dataModificaoFinamceiroADM.toLocalDateTime());

                }

                listaFinanceiroAdm.add(financeiroAdm);
            }

        } catch (SQLException erro) {

        }
    }

    public boolean inserePagamentoAvulsoEPagamentoFranquiaNoBancoDeDados(FinanceiroAdm financeiroAdm) {

        boolean pago = true;

        String pagamentoAvulso = "insert into financeiroadm (tipomovimento, valorfinanceiroadm, "
                + "idunidadefranquia, descritivomovimento, datacriacao) values (?,?,?,?,?)";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmPagamentoAvulso = connection.prepareStatement(pagamentoAvulso)) {

                pstmPagamentoAvulso.setString(1, financeiroAdm.getTipoMovimento());
                pstmPagamentoAvulso.setDouble(2, financeiroAdm.getValor());
                pstmPagamentoAvulso.setInt(3, financeiroAdm.getUnidadeFranquia().getIdUnidadeFranquia());
                pstmPagamentoAvulso.setString(4, financeiroAdm.getDescritivoMovimento());
                pstmPagamentoAvulso.setTimestamp(5, Timestamp.valueOf(financeiroAdm.getDataCriacao()));

                pstmPagamentoAvulso.execute();

                connection.commit();

            } catch (SQLException e) {
                pago = false;
                connection.rollback();
            }

        } catch (SQLException erro) {

        }
        return pago != false;
    }

    public boolean geraRelatorioGeralFinanceiroAdmEmPdf() {

        boolean gerado = true;

        String RESULT = "relatoriosPOOT2/relatoriaFinanceiroAdm.pdf";

        try {
            createPdf(RESULT);
        } catch (DocumentException | IOException ex) {
            gerado = false;
        }
        return gerado != false;
    }

    public void createPdf(String filename)
            throws DocumentException, IOException {

        Document document = new Document();

        PdfWriter.getInstance(document,
                new FileOutputStream(new File(filename)));

        document.open();

        //criando o paragrafo com o titulo do arquivo
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 26);
        Paragraph relaParagraph = new Paragraph("RELATORIO FINANCEIRO GERAL", font);
        relaParagraph.setAlignment(Element.ALIGN_CENTER);

        relaParagraph.getFont().setColor(BaseColor.DARK_GRAY);

        document.add(relaParagraph);

        document.add(Chunk.NEWLINE);

        document.add(createFirstTable());

        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {
            document.add(BuscaDadosDaColecao(financeiroAdm));
        }

        document.close();

    }

    public PdfPTable createFirstTable() {

        PdfPTable table = new PdfPTable(7);

        //area disponivel para a tabela
        table.setWidthPercentage(100);

        //altura das colunas
        int height = 30;

        PdfPCell cell;

        Phrase phrase = new Phrase("ID");
        phrase.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase2 = new Phrase("Movimento");
        phrase2.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase2);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase3 = new Phrase("Valor");
        phrase3.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase3);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase4 = new Phrase("Descrição");
        phrase4.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase4);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase5 = new Phrase("Data");
        phrase5.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase5);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase6 = new Phrase("Franquia");
        phrase6.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase6);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase7 = new Phrase("Unidade");
        phrase7.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase7);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);


        return table;
    }

    public PdfPTable BuscaDadosDaColecao(FinanceiroAdm financeiroAdm) {

        PdfPTable table = new PdfPTable(7);

        //area disponivel para a tabela
        table.setWidthPercentage(100);

        //altura das colunas
        int height = 40;

        String id = Integer.toString(financeiroAdm.getIdFinanceiroAdm());
        String movimento = financeiroAdm.getTipoMovimento();
        String valor = Double.toString(financeiroAdm.getValor());
        String descricao = financeiroAdm.getDescritivoMovimento();

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String data = financeiroAdm.getDataCriacao().format(formatador);
        String franquia = financeiroAdm.getUnidadeFranquia().getFranquia().getNomeFranquia();
        String unidade = financeiroAdm.getUnidadeFranquia().getCidadeUnidadeFranquia();

        PdfPCell cell;

        Phrase phrase = new Phrase(id);
        phrase.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase2 = new Phrase(movimento);
        phrase2.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase2);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase3 = new Phrase("R$ " + valor);
        phrase3.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase3);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase4 = new Phrase(descricao);
        phrase4.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase4);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase5 = new Phrase(data);
        phrase5.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase5);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase6 = new Phrase(franquia);
        phrase6.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase6);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Phrase phrase7 = new Phrase(unidade);
        phrase7.getFont().setColor(BaseColor.BLACK);
        cell = new PdfPCell(phrase7);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        return table;
    }

}
