package project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ConnectionUtils {

    private final Logger log = LoggerFactory.getLogger("project.ObjectFactory");
    private static ConnectionUtils INSTANCE = null;
    private final Properties props;
    private final Map<String, Object> context;

    private ConnectionUtils() {
        Properties props = new Properties();
        try (InputStream is = ClassLoader.getSystemResourceAsStream("application.properties")) {
            props.load(is);
        } catch (IOException e) {
            log.warn("Unable to read application.properties");
            System.err.println(e.getMessage());
        }
        this.props = props;
        this.context = new HashMap<>();
    }

    public static ConnectionUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (ConnectionUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionUtils();
                }
            }
        }

        return INSTANCE;
    }

    synchronized public Connection getConnection() {
        Connection conn = (Connection) context.get("connection");
        if (conn != null) {
            return conn;
        }
        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
        } catch (SQLException e) {
            log.warn("IN - getConnection - Instance creation error Connection.");
            e.printStackTrace();
        }
        log.info("IN - getConnection - Connection object created successfully.");
        context.put("connection", conn);

        return conn;
    }

    public Statement getStatement() throws SQLException {
        return getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }
}

