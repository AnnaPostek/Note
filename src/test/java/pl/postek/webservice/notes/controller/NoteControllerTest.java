package pl.postek.webservice.notes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import pl.postek.webservice.notes.model.Note;
import pl.postek.webservice.notes.repository.NoteRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(NoteController.class)
@SpringBootTest
@TestPropertySource(locations = "/application-integrationtest.properties")
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NoteRepository repository;

    @Test
    public void testListNotes() throws Exception {
        List<Note> listNote = new ArrayList<>();
        listNote.add(new Note(1, "buy", "dress", LocalDateTime.now(), null));
        listNote.add(new Note(2, "sell", "toys", LocalDateTime.now(), null));
        listNote.add(new Note(3, "sport", "running", LocalDateTime.now(), null));
        Mockito.when(repository.findAll()).thenReturn(listNote);
        String url = "/notes";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }
}