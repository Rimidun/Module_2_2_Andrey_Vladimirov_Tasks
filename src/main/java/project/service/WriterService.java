package project.service;

import project.entity.Writer;

public interface WriterService {
    Writer get(Long id);
    Writer getByFirstName(String firstName);
    Writer getByLastName(String lastName);
    Writer getByLabel(Long labelId);
    Writer update(Writer writer);
    Writer save(Writer writer);
    void remove(Writer writer);
}
