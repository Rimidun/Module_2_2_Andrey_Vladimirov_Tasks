package project.service.implementation;

import project.entity.Label;
import project.repository.LabelRepository;
import project.repository.implementation.JdbcLabelRepositoryImpl;
import project.service.LabelService;

public class JdbcLabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;

    public JdbcLabelServiceImpl() {
        this.labelRepository =  new JdbcLabelRepositoryImpl();
    }

    @Override
    public Label get(Long id) {
        return labelRepository.get(id);
    }

    @Override
    public Label get(String name) {
        return labelRepository.get(name);
    }



    @Override
    public Label update(Label label) {
        return labelRepository.update(label);
    }

    @Override
    public Label save(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public void remove(Label label) {
        labelRepository.remove(label.getId());
    }
}