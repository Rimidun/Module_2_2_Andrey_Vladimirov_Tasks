package project.repository.implementation;

import project.entity.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.repository.DBUtils;
import project.repository.PostRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepositoryImpl implements PostRepository {
    private final Logger log = LoggerFactory.getLogger("PostRepositoryImpl");

    @Override
    public Post save(Post entity) {
        try (Statement st = DBUtils.getStatement()) {
            int row = st.executeUpdate(String.format(DBUtils.SAVE_POST, entity.getWritersId(), entity.getContent()), Statement.RETURN_GENERATED_KEYS);
            if (row == 0) {
                log.warn("IN save - Note " + entity + " not saved.");
                return null;
            }
            if (row > 1) {
                log.warn("IN save - Save " + entity + " affected other notes.");
            }
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    return get(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Post get(Long id) {
        Post post = null;

        try (Statement st = DBUtils.getStatement()) {
            st.executeQuery(String.format(DBUtils.GET_POST_BY_ID, id));
            try (ResultSet rs = st.getResultSet()) {
                while (rs.next()) {

                    post = new Post(rs.getLong("id"),
                            rs.getLong("writers_id"),
                            rs.getString("content"),
                            rs.getTimestamp("create"),
                            rs.getTimestamp("upgrade")
                    );
                }
                return post;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post get(String content) {
        Post post = null;

        try (Statement st = DBUtils.getStatement()) {
            st.executeQuery(String.format(DBUtils.GET_POST_BY_CONTENT, content));
            try (ResultSet rs = st.getResultSet()) {
                while (rs.next()) {

                    post = new Post(rs.getLong("id"),
                            rs.getLong("writers_id"),
                            rs.getString("content"),
                            rs.getTimestamp("create"),
                            rs.getTimestamp("upgrade")
                    );
                }
                return post;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        try (Statement st = DBUtils.getStatement()) {
            int row = st.executeUpdate(String.format(DBUtils.UPDATE_POST, post.getContent(), post.getId()));
            if (row == 0) {
                log.warn("IN update - Failed to update note  " + post + ".");
                return null;
            }
            if (row > 1) {
                log.warn("IN update - Note update  " + post + " affected other notes.");
            }

            return post;
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void remove(Long id) {
        try (Statement st = DBUtils.getStatement()) {
            int row = st.executeUpdate(String.format(DBUtils.DELETE_POST, id));
            if (row == 0) {
                log.warn("IN remove - Note with id" + id + " not deleted.");
            }
            if (row > 1) {
                log.warn("IN remove - Delete note with id  " + id + " affected other notes.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> getAllByWriterId(Long writerId) {
        List<Post> posts = new ArrayList<>();
        try (
                Statement st = DBUtils.getStatement();
                ResultSet rs = st.executeQuery(String.format(DBUtils.GET_ALL_POST_BY_WRITER_ID, writerId));
        ) {
            while (rs.next()) {
                posts.add(new Post(
                        rs.getLong("id"),
                        rs.getLong("writers_id"),
                        rs.getString("content"),
                        rs.getTimestamp("create"),
                        rs.getTimestamp("upgrade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }
}
