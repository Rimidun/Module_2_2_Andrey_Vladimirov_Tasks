package project.service;

import project.entity.Label;

public interface LabelService {
    Label get(Long id);
    Label get(String name);
    Label update(Label label);
    Label save(Label label);
    void remove(Label label);
}
