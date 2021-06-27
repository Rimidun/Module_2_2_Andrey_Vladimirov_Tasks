package project.controllers;

import project.entity.Writer;
import project.service.WriterService;
import project.service.impl.WriterServiceImpl;

public class WriterController {
    private final WriterService writerService;

    public WriterController() {
        this.writerService = new WriterServiceImpl();
    }

    public Writer save(Writer writer){
        return writerService.save(writer);
    }

    public Writer get(Long id){
        return writerService.get(id);
    }

    public Writer getByFirstName(String firstName){
        return writerService.getByFirstName(firstName);
    }

    public Writer getByLastName(String lastName){
        return writerService.getByLastName(lastName);
    }

    public Writer getByLabel(Long labelId){
        return writerService.getByLabel(labelId);
    }

    public Writer update(Writer writer){
        return writerService.update(writer);
    }

    public void remove(Writer writer){
        writerService.remove(writer);
    }
}
