package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.mfu.domain.FlashCard;
import th.mfu.repository.FlashCardRepository;

@Controller
public class FlashCardController {

    // Injecting FlashCardRepository dependency using @Autowired
    @Autowired
    FlashCardRepository flashcardRepo;

    // Constructor for dependency injection
    public FlashCardController(FlashCardRepository flashcardRepo) {
        this.flashcardRepo = flashcardRepo;
    }

    // Handler method for displaying the list of flashcards
    @GetMapping("/flashcards")
    public String listFlashCard(Model model) {
        // Retrieves all flashcards from the repository and adds them to the model
        model.addAttribute("flashcards", flashcardRepo.findAll());
        return "flashcardmain";
    }

    // Handler method for displaying the form to add a new flashcard
    @GetMapping("/add-flashcard")
    public String addFlashCardForm(Model model) {
        // Adds a new FlashCard object to the model to be used in the form
        model.addAttribute("newflashcard", new FlashCard());
        return "CreateFlashCard";
    }

    // Handler method for saving a new flashcard
    @PostMapping("/flashcards")
    public String saveFlashCard(@ModelAttribute FlashCard newflashcard) {
        flashcardRepo.save(newflashcard);
        return "redirect:/flashcards";
    }

    // Handler method for displaying the form to edit a flashcard
    @GetMapping("/edit-flashcard/{id}")
    public String editFlashCardForm(@PathVariable Long id, Model model) {
        FlashCard flashcard = flashcardRepo.findById(id).orElse(null);
        model.addAttribute("editflashcard", flashcard);
        return "EditFlashCard";
    }

    // Handler method for updating a flashcard
    @PostMapping("/update-flashcard/{id}")
    public String updateFlashCard(@PathVariable Long id, @ModelAttribute FlashCard updatedFlashCard) {
        FlashCard flashcard = flashcardRepo.findById(id).orElse(null);
        // Updates the flashcard with the new data
        if (flashcard != null) {
            flashcard.setName(updatedFlashCard.getName());
            flashcard.setFrontLabel(updatedFlashCard.getFrontLabel());
            flashcard.setBackLabel(updatedFlashCard.getBackLabel());
            flashcardRepo.save(flashcard);
        }
        return "redirect:/flashcards";
    }

    // Handler method for deleting a flashcard
    @GetMapping("/delete-flashcard/{id}")
    public String deleteFlashCard(@PathVariable Long id) {
        // Deletes the flashcard by id from the repository
        flashcardRepo.deleteById(id);
        return "redirect:/flashcards";
    }
}
