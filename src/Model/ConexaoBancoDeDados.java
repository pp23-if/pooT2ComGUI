package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBancoDeDados {

    public Connection ConectaBancoDeDados() {

        Connection connect = null;

        try {
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "admin");
            properties.setProperty("useSSL", "false");
            properties.setProperty("useTimezone", "true");
            properties.setProperty("serverTimezone", "UTC");
            properties.setProperty("allowPublicKeyRetrieval", "true");

            String url = "jdbc:mysql://localhost:3306/poot2pedroaugustovieirafreitaselucasrochacaixeta";
            connect = DriverManager.getConnection(url, properties);

        } catch (SQLException erro) {
            erro.getMessage();
        }

        return connect;

    }

}
