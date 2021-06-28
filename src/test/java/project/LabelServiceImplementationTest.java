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
import project.service.implementation.LabelServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LabelServiceImplementationTest {

    @Spy
    @InjectMocks
    private LabelServiceImpl labelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void get_by_id() {
        Label label = labelService.get(2L);

        assertNotNull(label);
        assertEquals(2L, label.getId());
        assertNotNull(label.getName());
        Mockito.verify(labelService, Mockito.times(1)).get(Mockito.anyLong());
    }

    @Test
    void get_by_name() {
        Label label = labelService.get("work");

        assertNotNull(label);
        assertNotNull(label.getId());
        assertEquals("work", label.getName());
        Mockito.verify(labelService, Mockito.times(1)).get(Mockito.anyString());
    }

    @Test
    void update_some_label() {
        Label updatableLabel = labelService.get(4L);
        String oldName = updatableLabel.getName();
        String newName = "home";
        updatableLabel.setName(newName);
        updatableLabel = labelService.update(updatableLabel);

        assertNotNull(updatableLabel);
        assertEquals(4L, updatableLabel.getId());
        assertEquals("home", updatableLabel.getName());
        Mockito.verify(labelService, Mockito.times(1)).update(Mockito.any(Label.class));

        updatableLabel.setName(oldName);
        labelService.update(updatableLabel);
        Mockito.verify(labelService, Mockito.times(2)).update(Mockito.any(Label.class));

    }

    @Test
    void save_some_label() {
        Label saveLabel = new Label(null, "animal");
        saveLabel = labelService.save(saveLabel);

        assertNotNull(saveLabel.getId());
        assertEquals("animal", saveLabel.getName());
        Mockito.verify(labelService, Mockito.times(1)).save(Mockito.any(Label.class));
    }

    @Test
    void remove_some_label() {
        Label removeLabel = new Label(11L, "animal");
        labelService.remove(removeLabel);
        Label find = labelService.get(11L);

        assertNull(find);
        Mockito.verify(labelService, Mockito.times(1)).remove(Mockito.any(Label.class));
    }
}