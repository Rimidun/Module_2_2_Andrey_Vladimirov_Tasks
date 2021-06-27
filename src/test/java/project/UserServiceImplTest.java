package project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import project.entity.Label;
import project.entity.Post;
import project.entity.dto.WriterDto;
import project.service.imp.UserServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Spy
    @InjectMocks
    private UserServiceImpl userService;

    private WriterDto testWriterDto;
    private List<Post> posts;
    private Label label;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        posts = new ArrayList<>();
        posts.add(new Post(null, null, "Test content 1", new Date(), null));
        posts.add(new Post(null, null, "Test content 2", new Date(), null));
        posts.add(new Post(null, null, "Test content 3", new Date(), null));
        label = new Label(null, "Test label");
        testWriterDto = new WriterDto(null, "Test", "Dto", posts, label);
    }

    @Test
    void save_writer_as_writerDto() {
        WriterDto save = userService.save(testWriterDto);

        assertNotNull(save);
        assertEquals("Test label", save.getLabel().getName());
        assertEquals(posts.size(), save.getPosts().size());
        Mockito.verify(userService, Mockito.times(1)).save(Mockito.any(WriterDto.class));
    }

    @Test
    void update_some_writerDto() {
        WriterDto updatableWriterDto = userService.get(5L);
        String oldFirstName = updatableWriterDto.getFirstName();
        String newFirstName = "Testtest";
        updatableWriterDto.setFirstName(newFirstName);
        updatableWriterDto = userService.update(updatableWriterDto);

        assertNotNull(updatableWriterDto);
        assertEquals(5L, updatableWriterDto.getId());
        assertEquals("Testtest", updatableWriterDto.getFirstName());
        Mockito.verify(userService, Mockito.times(1)).update(Mockito.any(WriterDto.class));

        updatableWriterDto.setFirstName(oldFirstName);
        updatableWriterDto = userService.update(updatableWriterDto);

        assertEquals(5L, updatableWriterDto.getId());
        assertEquals(oldFirstName, updatableWriterDto.getFirstName());
        Mockito.verify(userService, Mockito.times(2)).update(Mockito.any(WriterDto.class));
    }

    @Test
    void get_some_writerDto_by_id() {
        WriterDto find = userService.get(2L);

        assertNotNull(find);
        assertEquals(2L, find.getId());
        Mockito.verify(userService, Mockito.times(1)).get(Mockito.anyLong());
    }

    @Test
    void get_by_first_name() {
        WriterDto find = userService.getByFirstName("Test");

        assertNotNull(find);
        assertEquals("Test", find.getFirstName());
        Mockito.verify(userService, Mockito.times(1)).getByFirstName(Mockito.anyString());
    }

    @Test
    void get_by_last_name() {
        WriterDto find = userService.getByLastName("Dto");

        assertNotNull(find);
        assertEquals("Dto", find.getLastName());
        Mockito.verify(userService, Mockito.times(1)).getByLastName(Mockito.anyString());
    }

    @Test
    void remove_some_writerDto() {
        userService.remove(19L);
        WriterDto find = userService.get(19L);

        assertNull(find);
        Mockito.verify(userService, Mockito.times(1)).remove(Mockito.anyLong());
    }
}
