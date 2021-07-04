package project.service.implementation;

import project.entity.Post;
import project.repository.PostRepository;
import project.repository.implementation.JdbcPostRepositoryImpl;
import project.service.PostService;

import java.util.List;

public class JdbcPostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public JdbcPostServiceImpl() {
        this.postRepository = new JdbcPostRepositoryImpl();
    }

    @Override
    public Post get(Long id) {
        return postRepository.get(id);
    }

    @Override
    public Post get(String content) {
        return postRepository.get(content);
    }

    @Override
    public List<Post> getAll(Long writerId) {
        return postRepository.getAllByWriterId(writerId);
    }

    @Override
    public Post update(Post post) {
        return postRepository.update(post);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void remove(Post post) {
        postRepository.remove(post.getId());
    }
}