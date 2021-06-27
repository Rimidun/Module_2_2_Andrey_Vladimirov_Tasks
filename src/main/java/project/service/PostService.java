package project.service;

import project.entity.Post;

import java.util.List;

public interface PostService {
    Post get(Long id);
    Post get(String content);
    List<Post> getAll(Long writerId);
    Post update(Post post);
    Post save(Post post);
    void remove(Post post);

}
