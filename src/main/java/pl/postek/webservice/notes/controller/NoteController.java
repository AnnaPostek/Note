package pl.postek.webservice.notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.postek.webservice.notes.exception.NoteNotFoundException;
import pl.postek.webservice.notes.model.Note;
import pl.postek.webservice.notes.service.NoteService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/note/{id}")
    public ResponseEntity<Object> getNote(@PathVariable int id) {
        Note noteById = service.getNoteById(id);
        return new ResponseEntity<>(noteById, HttpStatus.OK);
    }

    @PostMapping("/addNote")
    public ResponseEntity<Object> saveNote(@RequestBody @Valid Note note) {
        Note savedNote = service.saveNote(note);
        return new ResponseEntity<>("Note is created successfully with Id = " + savedNote.getId(),
                HttpStatus.CREATED);
    }

    @PutMapping("/updateNote/{id}")
    public ResponseEntity<Object> updateNote(@PathVariable int id, @RequestBody @Valid Note note) {
        if (service.noteExistsById(id)) {
            Note savedNote = service.updateNote(id, note);
            return new ResponseEntity<>(String.format("Note with id = %d is updated successfully", savedNote.getId()),
                    HttpStatus.OK);
        } else
            throw new NoteNotFoundException();
    }

    @DeleteMapping("/deleteNote/{id}")
    public ResponseEntity<Object> deleteNote(@PathVariable int id) {
        if (service.noteExistsById(id)) {
            service.deleteNote(id);
            return new ResponseEntity<>(String.format("Note with id = %d is deleted successfully", id),
                    HttpStatus.OK);
        } else {
            throw new NoteNotFoundException();
        }
    }

    @GetMapping("/revision/{id}")
    public ResponseEntity<Object> lastChangeRevision(@PathVariable int id) {
        if (service.noteExistsById(id)) {
            service.getInfoLastChangeRevision(id);
            return new ResponseEntity<>("service.getInfoLastChangeRevision(id)", HttpStatus.OK);
        }
        throw new NoteNotFoundException();
    }

    @ExceptionHandler(value = NoteNotFoundException.class)
    public ResponseEntity<Object> exception(NoteNotFoundException exception) {
        return new ResponseEntity<>("Note not found", HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
