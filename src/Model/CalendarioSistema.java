package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CalendarioSistema {

    //private DateTimeFormatter fd = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private LocalDateTime dataHoraSistema = LocalDateTime.of(2023, 1, 25, 8, 0, 0);
    private LocalDateTime hoje;
    private LocalDateTime amanha;
    private LocalDate diaDoSistema = this.dataHoraSistema.toLocalDate();

    public LocalDateTime getDataHoraSistema() {
        return dataHoraSistema;
    }

    public void setDataHoraSistema(LocalDateTime dataHoraSistema) {
        this.dataHoraSistema = dataHoraSistema;
    }

    public LocalDate getDiaDoSistema() {
        return diaDoSistema;
    }

    public boolean passaDias(int dias) {

        hoje = this.dataHoraSistema;

        this.setDataHoraSistema(dataHoraSistema.plusDays(dias));
        amanha = this.dataHoraSistema;

        diaDoSistema = amanha.toLocalDate();

        if (verificaSeDiaPassou(hoje, amanha) == true) {
            return true;
        } else {
            return false;
        }

    }

    private boolean verificaSeDiaPassou(LocalDateTime hojeDataSistema,
            LocalDateTime amanhaDataSistema) {

        if (amanhaDataSistema.isAfter(hojeDataSistema)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean passaDiasnoBancoDeDados(LocalDateTime dataHoraSistemaNoBanco) {

        String atualizaDiasNoBanco = "update calendariosistema set datahorasistema = ?";
        boolean passaDia = true;
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados(); 
                PreparedStatement pstm = connection.prepareStatement(atualizaDiasNoBanco)) {

            pstm.setTimestamp(1, Timestamp.valueOf(dataHoraSistemaNoBanco));
            pstm.execute();
            passaDia = true;
        } 
        catch (SQLException e) {
            passaDia = false;
        }
        return passaDia != false;
    }

    public void buscaDataHoraSistemaNoBancoDeDados() {

        String buscaDataHoraSistema = "select * from calendariosistema";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados(); PreparedStatement pstm = connection.prepareStatement(buscaDataHoraSistema); ResultSet rs = pstm.executeQuery(buscaDataHoraSistema)) {

            while (rs.next()) {
                Timestamp dataHoraSistema = rs.getTimestamp("datahorasistema");
                setDataHoraSistema(dataHoraSistema.toLocalDateTime());
            }
        } catch (SQLException e) {

        }
    }
}
