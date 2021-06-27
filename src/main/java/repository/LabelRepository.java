package repository;

import entity.Label;

public interface LabelRepository extends GenericRepository<Label, Long> {
    Label get(String name);
}