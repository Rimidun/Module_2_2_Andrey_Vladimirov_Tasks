package project.service.implementation;

import project.entity.Writer;
import project.repository.WriterRepository;
import project.repository.implementation.JdbcWriterRepositoryImpl;
import project.service.WriterService;

public class JdbcWriterServiceImpl implements WriterService {
    private final WriterRepository writerRepository;

    public JdbcWriterServiceImpl() {
        this.writerRepository = new JdbcWriterRepositoryImpl();
    }

    @Override
    public Writer get(Long id) {
        return writerRepository.get(id);
    }

    @Override
    public Writer getByFirstName(String firstName) {
        return writerRepository.getByFirstName(firstName);
    }

    @Override
    public Writer getByLastName(String lastName) {
        return writerRepository.getByLastName(lastName);
    }

    @Override
    public Writer getByLabel(Long labelId) {
        return writerRepository.getByLabel(labelId);
    }

    @Override
    public Writer update(Writer writer) {
        return writerRepository.update(writer);
    }

    @Override
    public Writer save(Writer writer) {
        return writerRepository.save(writer);
    }

    @Override
    public void remove(Writer writer) {
        writerRepository.remove(writer.getId());
    }
}
