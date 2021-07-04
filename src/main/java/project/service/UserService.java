package project.service;

import project.entity.dto.WriterDto;

public interface UserService {
    WriterDto save(WriterDto writerDto);
    WriterDto update(WriterDto writerDto);
    WriterDto get(Long id);
    void remove(Long id);

    WriterDto getByFirstName(String firstName);
    WriterDto getByLastName(String lastName);
}
