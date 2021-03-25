package pl.postek.webservice.notes.controller;

import org.springframework.data.history.Revision;
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
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NoteController {

    private NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> allNotes = service.getAllNotes();
        return new ResponseEntity<>(allNotes, HttpStatus.OK);
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<Object> getNote(@PathVariable int id) {
        Note noteById = service.getNoteById(id);
        return new ResponseEntity<>(noteById, HttpStatus.OK);
    }

    @PostMapping("/note")
    public ResponseEntity<Object> saveNote(@RequestBody @Valid Note note) {
        Note savedNote = service.saveNote(note);
        return new ResponseEntity<>("Note is created successfully with Id = " + savedNote.getId(),
                HttpStatus.CREATED);
    }

    @PutMapping("/note/{id}")
    public ResponseEntity<Object> updateNote(@PathVariable int id, @RequestBody @Valid Note note) {
        if (service.noteExistsById(id)) {
            Note savedNote = service.updateNote(id, note);
            return new ResponseEntity<>(String.format("Note with id = %d is updated successfully", savedNote.getId()),
                    HttpStatus.OK);
        } else
            throw new NoteNotFoundException();
    }

    @DeleteMapping("/note/{id}")
    public ResponseEntity<Object> deleteNote(@PathVariable int id) {
        if (service.noteExistsById(id)) {
            service.deleteNote(id);
            return new ResponseEntity<>(String.format("Note with id = %d is deleted successfully", id),
                    HttpStatus.OK);
        } else {
            throw new NoteNotFoundException();
        }
    }

    @GetMapping("/notes/{id}/revisions")
    public ResponseEntity<List<Note>> historyChangeRevision(@PathVariable int id) {
        List<Note> noteRevisionList = service.getRevisionsForNote(id).get().map(Revision::getEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(noteRevisionList, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
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
