package project.repository;


import project.ConnectionUtils;


import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

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
        return ConnectionUtils.getInstance()
                .getStatement();
    }
}
