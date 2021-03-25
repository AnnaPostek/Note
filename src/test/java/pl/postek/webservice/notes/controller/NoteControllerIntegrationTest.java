package pl.postek.webservice.notes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.postek.webservice.notes.model.Note;
import pl.postek.webservice.notes.service.NoteService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NoteService service;

    @Test
    public void shouldReturnCreateStatus_whenNoteIsSaved() throws Exception {
        Note note = getBasicNote();
        service.saveNote(note);
        this.mockMvc
                .perform(post("/note")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(note)))
                .andExpect(status().isCreated());
        Note foundNote = service.getNoteById(note.getId());
        assertEquals(foundNote.getTitle(), "learn");
    }

    @Test
    public void shouldReturnOkStatus_whenDisplayListOfNotes() throws Exception {
        Note note1 = new Note(1, "learn", "java", LocalDateTime.now(), LocalDateTime.now());
        Note note2 = new Note(2, "sport", "jump", LocalDateTime.now(), LocalDateTime.now());
        Note note3 = new Note(3, "watch", "tv", LocalDateTime.now(), LocalDateTime.now());
        List<Note> noteList = new ArrayList<>();
        noteList.add(note1);
        noteList.add(note2);
        noteList.add(note3);
        this.mockMvc
                .perform(get("/notes")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noteList.toString())))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatus_whenDeleteNoteById() throws Exception {
        Note note = getBasicNote();
        service.saveNote(note);
        int id = service.getAllNotes().get(0).getId();
        this.mockMvc
                .perform(delete("/note/{id}", String.valueOf(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatus_whenDisplayNoteById() throws Exception {
        Note note = getBasicNote();
        service.saveNote(note);
        int id = service.getAllNotes().get(0).getId();
        this.mockMvc
                .perform(get("/note/{id}", String.valueOf(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatus_whenDisplayRevisionsListOfNotes() throws Exception {
        Note note = getBasicNote();
        service.saveNote(note);
        int id = service.getAllNotes().get(0).getId();
        this.mockMvc
                .perform(get("/notes/{id}/revisions", String.valueOf(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatus_whenUpdateNote() throws Exception {
        Note note = getBasicNote();
        service.saveNote(note);
        int id = service.getAllNotes().get(0).getId();
        String title = "title";
        String content = "content";
        this.mockMvc
                .perform(get("/notes/{id}/revisions", String.valueOf(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(title))
                        .content(objectMapper.writeValueAsString(content)))
                .andExpect(status().isOk());
    }

    private Note getBasicNote() {
        Note note = new Note();
        note.setTitle("learn");
        note.setContent("java");
        note.setCreated(LocalDateTime.now());
        note.setModified(LocalDateTime.now());
        return note;
    }
}
