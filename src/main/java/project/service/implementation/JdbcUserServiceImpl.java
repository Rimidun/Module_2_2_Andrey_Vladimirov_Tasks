package project.service.implementation;

import project.entity.Label;
import project.entity.Post;
import project.entity.Writer;
import project.entity.dto.WriterDto;
import project.repository.LabelRepository;
import project.repository.PostRepository;
import project.repository.WriterRepository;
import project.repository.implementation.JdbcLabelRepositoryImpl;
import project.repository.implementation.JdbcPostRepositoryImpl;
import project.repository.implementation.JdbcWriterRepositoryImpl;
import project.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class JdbcUserServiceImpl implements UserService {
    private final LabelRepository labelRepository;
    private final PostRepository postRepository;
    private final WriterRepository writerRepository;

    public JdbcUserServiceImpl() {
        this.labelRepository = new JdbcLabelRepositoryImpl();
        this.postRepository = new JdbcPostRepositoryImpl();
        this.writerRepository = new JdbcWriterRepositoryImpl();
    }

    @Override
    public WriterDto save(WriterDto writerDto) {
        Writer writer = WriterDto.fromWriterDto(writerDto);
        Label label = writerDto.getLabel();
        List<Post> posts = writerDto.getPosts();

        label = labelRepository.save(label);

        writer.setLabels_id(label.getId());
        writer.setFirstName(writerDto.getFirstName());
        writer.setLastName(writerDto.getLastName());
        writer = writerRepository.save(writer);
        Long writerId = writer.getId();

        posts = posts.stream()
                .peek(p -> p.setWritersId(writerId))
                .map(postRepository::save)
                .collect(Collectors.toList());

        writerDto.setId(writer.getId());
        return writerDto;
    }

    @Override
    public WriterDto update(WriterDto writerDto) {
        Writer writer = writerRepository.get(writerDto.getId());
        Label label = labelRepository.get(writerDto.getLabel().getName());
        if(label == null){
            label = writerDto.getLabel();
            labelRepository.save(label);
        } else labelRepository.update(label);
        writer.setFirstName(writerDto.getFirstName());
        writer.setLastName(writerDto.getLastName());
        writer.setLabels_id(label.getId());
        writerRepository.update(writer);
        List<Post> posts = writerDto.getPosts().stream().peek(p -> postRepository.update(p)).collect(Collectors.toList());

        return writerDto;
    }

    @Override
    public WriterDto get(Long id) {
        Writer writer = writerRepository.get(id);
        if (writer == null) {
            return null;
        }
        Label label = labelRepository.get(writer.getLabels_id());
        List<Post> posts = postRepository.getAllByWriterId(writer.getId());

        return WriterDto.fromWriter(writer);
    }

    public WriterDto getByFirstName(String firstName) {
        Writer writer = writerRepository.getByFirstName(firstName);
        if (writer == null) {
            return null;
        } else {
            Label label = labelRepository.get(writer.getLabels_id());
            List<Post> posts = postRepository.getAllByWriterId(writer.getId());

            return WriterDto.fromWriter(writer);
        }
    }

    public WriterDto getByLastName(String lastName) {
        Writer writer = writerRepository.getByLastName(lastName);
        if (writer == null) {
            return null;
        } else {
            Label label = labelRepository.get(writer.getLabels_id());
            List<Post> posts = postRepository.getAllByWriterId(writer.getId());

            return WriterDto.fromWriter(writer);
        }
    }

    @Override
    public void remove(Long id) {
        Writer writer = writerRepository.get(id);
        if(writer != null){
            writerRepository.remove(id);
        }
    }
}