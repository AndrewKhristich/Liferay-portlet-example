package spring.portlet.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class MyDataSource {


    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test2";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";


    private HikariDataSource ds;


    @PostConstruct
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(DB_DRIVER);
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
        return ds;
    }

    public void createAllTables() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Path.DROP_TABLES_SQL_QUERY)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(Path.TABLES_SQL_QUERY)) {
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


}