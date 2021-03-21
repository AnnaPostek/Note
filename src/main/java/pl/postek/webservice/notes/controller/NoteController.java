package pl.postek.webservice.notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.postek.webservice.notes.exception.NoteNotFoundException;
import pl.postek.webservice.notes.model.Note;
import pl.postek.webservice.notes.service.NoteService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NoteController {

    private NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/allNotes")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> allNotes = service.getAllNotes();
        return new ResponseEntity<>(allNotes, HttpStatus.OK);
    }

    @PostMapping("/addNote")
    public ResponseEntity<Object> saveNote(@RequestBody @Valid Note note) {
        Note savedNote = service.saveNote(note);
        return new ResponseEntity<>("Note is created successfully with Id = " + savedNote.getId(),
                HttpStatus.CREATED);
    }

    @PutMapping("/updateNote/{id}")
    public ResponseEntity<Object> updateNote(@PathVariable Long id, @RequestBody @Valid Note note) {
        if (service.noteExistsById(id)) {
            Note savedNote = service.updateNote(id, note);
            return new ResponseEntity<>(String.format("Note with id = %d is updated successfully", savedNote.getId()),
                    HttpStatus.OK);
        } else
            throw new NoteNotFoundException(String.format("Note with id = %d not found", id));
    }

    @DeleteMapping("/deleteNote/{id}")
    public ResponseEntity<Object> deleteNote(@PathVariable Long id) {
        if (service.noteExistsById(id)) {
            service.deleteNote(id);
            return new ResponseEntity<>(String.format("Note with id = %d is deleted successfully", id),
                    HttpStatus.OK);
        } else {
            throw new NoteNotFoundException(String.format("Note with id = %d not found", id));
        }
    }

    @GetMapping("/revision/{id}")
    public ResponseEntity<Object> lastChangeRevision(@PathVariable Long id) {
        if (service.noteExistsById(id)) {
            service.getInfoLastChangeRevision(id);
            return new ResponseEntity<>("service.getInfoLastChangeRevision(id)", HttpStatus.OK);
        }
        throw new NoteNotFoundException(String.format("Note with id = %d not found", id));
    }
}
