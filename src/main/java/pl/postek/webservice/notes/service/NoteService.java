package pl.postek.webservice.notes.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.postek.webservice.notes.model.Note;
import pl.postek.webservice.notes.repository.NoteRepository;

import java.util.List;

@Service
public class NoteService {

    private NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    @Transactional
    public Note saveNote(Note note) {
        return repository.save(note);
    }

    public Note getNoteById(Long id) {
        return repository.findById(id).get();
    }


    public Note updateNote(Long id, Note toUpdate) {
        Note byId = repository.findById(id).get();
        byId.setTitle(toUpdate.getTitle());
        byId.setContent(toUpdate.getContent());
        Note save = repository.save(byId);
        return save;
    }

    public void deleteNote(Long id) {
        repository.deleteById(id);
    }

    public void getInfoLastChangeRevision(Long id) {
        System.out.println(repository.findLastChangeRevision(id));
    }

    public void getRevision(Long id) {
        repository.findRevision(id, 2L);
    }
}
