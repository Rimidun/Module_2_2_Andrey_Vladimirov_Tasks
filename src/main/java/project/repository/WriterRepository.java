package project.repository;

import project.entities.Writer;

public interface WriterRepository extends GenericRepository<Writer, Long> {

    Writer getByFirstName(String firstName);
    Writer getByLastName(String lastName);
    Writer getByLabel(Long labelId);
}
