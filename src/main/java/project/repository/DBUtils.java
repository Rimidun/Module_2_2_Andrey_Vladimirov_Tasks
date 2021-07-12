package project.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBUtils {

    private final Logger log = LoggerFactory.getLogger("project.DBUtils");
    private static DBUtils INSTANCE = null;
    private final Properties props;
    private final Map<String, Object> context;

    private DBUtils() {
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

    public static DBUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (DBUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBUtils();
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

    public Statement getStatementBegin() throws SQLException {
        return getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public static String GET_LABEL_BY_ID = "SELECT `id`, `name` FROM labels WHERE id=%d";

    public static String GET_LABEL_BY_NAME = "SELECT `id`, `name` FROM labels WHERE name='%s'";

    public static String SAVE_LABEL = "INSERT labels VALUES(null, '%s')";

    public static String UPDATE_LABEL = "UPDATE `labels` SET name='%s' WHERE id=%d";

    public static String DELETE_LABEL = "DELETE FROM labels WHERE id=%d";


    public static String GET_POST_BY_ID = "SELECT * FROM posts WHERE id=%d";

    public static String GET_POST_BY_CONTENT = "SELECT * FROM posts WHERE content='%s'";

    public static String SAVE_POST = "INSERT INTO posts(writers_id, content) VALUES('%d', '%s')";

    public static String UPDATE_POST = "UPDATE posts SET content='%s' WHERE id=%d";

    public static String DELETE_POST = "DELETE FROM posts WHERE id=%d";

    public static String GET_ALL_POST_BY_WRITER_ID = "SELECT * FROM posts WHERE writers_id=%d";


    public static String GET_WRITER_BY_ID = "SELECT writers_id AS id, labels_id, first_name, last_name, labels.`name` AS labelName, posts.id AS postId, `content`, `create`, upgrade\n" +
            "FROM writers INNER JOIN labels ON labels.id=writers.labels_id INNER JOIN posts ON posts.writers_id=writers.id\n" +
            "WHERE writers.id=";

    public static String GET_WRITER_BY_FIRST_NAME = "SELECT writers_id AS id, labels_id, first_name, last_name, labels.`name` AS labelName, posts.id AS postId, `content`, `create`, upgrade\n" +
            "FROM writers INNER JOIN labels ON labels.id=writers.labels_id INNER JOIN posts ON posts.writers_id=writers.id\n" +
            "WHERE writers.first_name=";

    public static String GET_WRITER_BY_LAST_NAME = "SELECT writers_id AS id, labels_id, first_name, last_name, labels.`name` AS labelName, posts.id AS postId, `content`, `create`, upgrade\n" +
            "FROM writers INNER JOIN labels ON labels.id=writers.labels_id INNER JOIN posts ON posts.writers_id=writers.id\n" +
            "WHERE writers.last_name=";

    public static String GET_WRITER_BY_LABEL_ID = "SELECT writers_id AS id, labels_id, first_name, last_name, labels.`name` AS labelName, posts.id AS postId, `content`, `create`, upgrade\n" +
            "FROM writers INNER JOIN labels ON labels.id=writers.labels_id INNER JOIN posts ON posts.writers_id=writers.id\n" +
            "WHERE writers.labels_id=";

    public static String UPDATE_WRITER = "UPDATE writers SET labels_id=%d, first_name='%s', last_name='%s' Where id=%d";

    public static String DELETE_WRITER = "DELETE FROM writers WHERE id=%d";

    public static String SAVE_WRITER = "INSERT INTO writers VALUES(null, '%d', '%s', '%s')";


    public static Statement getStatement() throws SQLException {
        return DBUtils.getInstance()
                .getStatementBegin();
    }
}
