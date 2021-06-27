package project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Label {
    private Long id;
    private String name;
    //private List<Label> labels;

    public Label(String name) {
        this.id = null;
        this.name = name;
    }
}
