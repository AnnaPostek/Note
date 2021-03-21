package pl.postek.webservice.notes.controller;

import org.springframework.web.bind.annotation.*;
import pl.postek.webservice.notes.model.Note;
import pl.postek.webservice.notes.service.NoteService;

import java.util.List;

@RestController
public class NoteController {

    private NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/allNotes")
    public List<Note> getAllNotes() {
       return service.getAllNotes();
    }

    @PostMapping("/addNote")
    public Note saveNote(@RequestBody Note note) {
        return service.saveNote(note);
    }

    @PutMapping("/updateNote/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note note) {
        return service.updateNote(id, note);
    }

    @DeleteMapping("/deleteNote/{id}")
    public void deleteNote(@PathVariable Long id) {
        service.deleteNote(id);
    }

    @GetMapping("/revision/{id}")
    public void lastChangeRevision(@PathVariable Long id) {
        service.getInfoLastChangeRevision(id);
    }
}
