package project.entities.dto;

import project.entities.Label;
import project.entities.Post;
import project.entities.Writer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriterDto {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Post> posts;
    private Label label;

    public static WriterDto fromWriter(Writer writer){
        return new WriterDto(
                writer.getId(),
                writer.getFirstName(),
                writer.getLastName(),
                writer.getPosts(),
                writer.getLabel()
        );
    }

    public static Writer fromWriterDto(WriterDto writerDto){
        return new Writer(
                writerDto.getId(),
                null,
                writerDto.getFirstName(),
                writerDto.getLastName(),
                writerDto.getPosts(),
                writerDto.getLabel()
        );
    }
}
