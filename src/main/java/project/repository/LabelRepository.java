package project.repository;

import project.entity.Label;

public interface LabelRepository extends GenericRepository<Label, Long> {
    Label get(String name);
}