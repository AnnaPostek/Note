package pl.postek.webservice.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import pl.postek.webservice.notes.model.Note;

@Repository
public interface NoteRepository extends RevisionRepository<Note, Integer, Integer> , JpaRepository<Note, Integer> {
    boolean existsById(Integer id);
}
