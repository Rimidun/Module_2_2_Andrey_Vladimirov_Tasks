package service.impl;

import entity.Label;
import repository.LabelRepository;
import repository.impl.LabelRepositoryImpl;
import service.LabelService;

public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;

    public LabelServiceImpl() {
        this.labelRepository =  new LabelRepositoryImpl();
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