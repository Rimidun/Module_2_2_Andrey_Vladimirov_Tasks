package project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import project.entities.Label;
import project.entities.Post;
import project.entities.Writer;
import project.service.implementation.WriterServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WriterServiceImplementationTest {

    @Spy
    @InjectMocks
    private WriterServiceImpl writerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void get_by_id() {
        Writer find = writerService.get(2L);

        assertNotNull(find);
        assertEquals(2L, find.getId());
        assertNotNull(find.getPosts());
        assertNotNull(find.getLabel());
        Mockito.verify(writerService, Mockito.times(1)).get(Mockito.anyLong());
        System.out.println(find);
    }

    @Test
    void get_by_first_name() {
        Writer find = writerService.getByFirstName("Pavel");

        assertNotNull(find);
        assertEquals("Pavel", find.getFirstName());
        assertNotNull(find.getPosts());
        assertNotNull(find.getLabel());
        Mockito.verify(writerService, Mockito.times(1)).getByFirstName(Mockito.anyString());
        System.out.println(find);
    }

    @Test
    void get_by_last_name() {
        Writer find = writerService.getByLastName("User");

        assertNotNull(find);
        assertEquals("User", find.getLastName());
        assertNotNull(find.getPosts());
        assertNotNull(find.getLabel());
        Mockito.verify(writerService, Mockito.times(1)).getByLastName(Mockito.anyString());
        System.out.println(find);
    }

    @Test
    void get_by_label() {
        Writer find = writerService.getByLabel(2L);

        assertNotNull(find);
        assertEquals(2L, find.getLabels_id());
        assertNotNull(find.getPosts());
        assertNotNull(find.getLabel());
        Mockito.verify(writerService, Mockito.times(1)).getByLabel(Mockito.anyLong());
        System.out.println(find);
    }

    @Test
    void update_some_writer() {
        Writer updatableWriter = writerService.get(5L);
        Writer updatedWriter = new Writer();
        updatedWriter.setId(updatableWriter.getId());
        updatedWriter.setLabels_id(2L);
        updatedWriter.setFirstName("Sergey");
        updatedWriter.setLastName("Panti");
        updatedWriter = writerService.update(updatedWriter);

        assertNotNull(updatedWriter);
        assertEquals(5L, updatedWriter.getId());
        assertEquals(2L, updatedWriter.getLabels_id());
        assertEquals("Sergey", updatedWriter.getFirstName());
        assertEquals("Panti", updatedWriter.getLastName());
        Mockito.verify(writerService, Mockito.times(1)).update(Mockito.any(Writer.class));

        updatedWriter = writerService.update(updatableWriter);
        assertNotNull(updatedWriter);
        assertEquals(5L, updatedWriter.getId());
        assertEquals(5L, updatedWriter.getLabels_id());
        assertEquals("Goodtest", updatedWriter.getFirstName());
        assertEquals("root", updatedWriter.getLastName());
        Mockito.verify(writerService, Mockito.times(2)).update(Mockito.any(Writer.class));
    }


    @Test
    void save_some_writer() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(null, null, "Post Test", new Date(), null));
        Writer writer = new Writer(null, 9L, "Oleg", "Vovavich", posts, new Label(2L, "work"));
        writer = writerService.save(writer);

        assertNotNull(writer);
        assertNotNull(writer.getId());
        assertEquals(9L, writer.getLabels_id());
        assertEquals("Oleg", writer.getFirstName());
        assertEquals("Vovavich", writer.getLastName());
        Mockito.verify(writerService, Mockito.times(1)).save(Mockito.any(Writer.class));
    }

    @Test
    void remove() {
        Writer writer = new Writer(12L, 9L, "Oleg", "Vovavich", null, null);
        writerService.remove(writer);
        Writer find = writerService.get(12L);

        assertNull(find);
        Mockito.verify(writerService, Mockito.times(1)).remove(Mockito.any(Writer.class));
    }
}
