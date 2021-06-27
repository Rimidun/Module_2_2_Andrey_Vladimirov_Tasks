package project.repository;

import project.entities.Label;

public interface LabelRepository extends GenericRepository<Label, Long> {
    Label get(String name);
}