package th.mfu.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.mfu.domain.Note;
import th.mfu.repository.NoteRepository;


@Controller
public class NoteController {
    @Autowired
    NoteRepository noteRepo;


    // Handler method for saving a new note
    @PostMapping("/save-notes")
    public String saveNote(@ModelAttribute Note newnote) {
        // Save the new note to the repository
        noteRepo.save(newnote);
        return "redirect:/notes";
    }


    // Handler method for displaying the form to add a new note
    @GetMapping("/add-notes")
    public String addNote(Model model) {
        // Add a new Note object to the model to be used in the form
        model.addAttribute("note", new Note());
        return "add-notes";
    }


    // Handler method for displaying the list of notes
    @GetMapping("/notes")
    public String listNote(Model model) {
        model.addAttribute("notes", noteRepo.findAll());
        return "notes";
    }


    // Handler method for deleting a note
    @GetMapping("/delete-notes/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteRepo.deleteById(id);
        return "redirect:/notes";
    }


    // Handler method for searching notes by name
    @GetMapping("/search-notes")
    public String searchNotes(@RequestParam String name, Model model) {
        List<Note> searchResults = noteRepo.findByName(name);
        model.addAttribute("notes", searchResults);
        return "notes";
    }


}
