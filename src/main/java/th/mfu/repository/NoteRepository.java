package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import th.mfu.domain.Note;

public interface NoteRepository extends CrudRepository<Note, Long> {
    
     List<Note> findByName(@Param("name") String name);

}
