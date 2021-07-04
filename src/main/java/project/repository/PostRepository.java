package project.repository;

import project.entity.Post;

import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {
    List<Post> getAllByWriterId(Long writerId);
    Post get(String content);

}
