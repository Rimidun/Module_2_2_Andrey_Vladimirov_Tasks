package project.controller;

import project.entity.Label;
import project.service.LabelService;
import project.service.implementation.JdbcLabelServiceImpl;

public class LabelController {
    private final LabelService labelService;

    public LabelController() {
        this.labelService = new JdbcLabelServiceImpl();
    }

    public Label get(Long id) {
        return labelService.get(id);
    }

    public Label get(String name){
        return labelService.get(name);
    }

    public Label update(Label label) {
        return labelService.update(label);
    }

    public Label save(Label label) {
        return labelService.save(label);
    }

    public void remove(Label label) {
        labelService.remove(label);
    }
}
