package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import antlr.collections.List;
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
        return "note-search";
    }

    // Handler method for deleting a note
    @GetMapping("/delete-notes/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteRepo.deleteById(id);
        return "redirect:/notes";
    }

    // @GetMapping("/user-search-tea")
    // public String searchTea(Model model) {
    //     // model.addAttribute("dishes", dishesRepo.findByDishtype("tea"));
    //     Iterable<Dishes> activeTeaDishes = dishesRepo.findByDishtypeAndDishStatus("tea", "active");
    //     model.addAttribute("dishes", activeTeaDishes);

    //     // only show InvoiceItem that invoice = null
    //     model.addAttribute("invoiceitem", invoiceItemRepo.findByInvoiceIsNull());
    //     return "user";
    // }

    // @PostMapping("/notes/search")
    // public String searchNotes(@RequestParam(name = "query", required = false) String query, Model model) {
    //     // Perform the search using your service
    //     Iterable<Note> searchResults = noteRepo.searchNotes(query);

    //     // Add the search results to the model
    //     model.addAttribute("searchResults", searchResults);

    //     return "search-results"; // Thymeleaf template name: "searchResults.html"
    // }

}
