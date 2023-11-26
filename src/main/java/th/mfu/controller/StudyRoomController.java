package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.mfu.domain.StudyRoom;
import th.mfu.repository.StudyRoomRepository;

@Controller
public class StudyRoomController {

    // Injecting StudyRoomRepository dependency using @Autowired
    @Autowired
    StudyRoomRepository studyroomRepo;

    // Constructor for dependency injection
    public StudyRoomController(StudyRoomRepository studyroomRepo) {
        this.studyroomRepo = studyroomRepo;
    }

    // Handler method for displaying the list of study rooms
    @GetMapping("/studyrooms")
    public String listStudyRoom(Model model) {
        model.addAttribute("flashcards", studyroomRepo.findAll());
        return "StudyRoomMain";
    }

    // Handler method for displaying the form to add a new study room
    @GetMapping("/add-studyroom")
    public String addStudyRoomForm(Model model) {
        model.addAttribute("newstudyroom", new StudyRoom());
        return "createStudyRoom";
    }

    // Handler method for saving a new study room
    @PostMapping("/studyrooms")
    public String saveStudyRoom(@ModelAttribute StudyRoom newstudyroom) {
        studyroomRepo.save(newstudyroom);
        return "redirect:/studyrooms";
    }

    // Handler method for displaying the form to edit a study room
    @GetMapping("/edit-studyroom/{id}")
    public String editStudyRoomForm(@PathVariable Long id, Model model) {
        StudyRoom studyroom = studyroomRepo.findById(id).orElse(null);
        model.addAttribute("editstudyroom", studyroom);
        return "EditStudyRoom";
    }

    // Handler method for updating a study room
    @PostMapping("/update-studyroom/{id}")
    public String updateStudyRoom(@PathVariable Long id, @ModelAttribute StudyRoom updatedStudyRoom) {
        // Retrieves the study room by id from the repository
        StudyRoom studyroom = studyroomRepo.findById(id).orElse(null);
        // Updates the study room with the new data
        if (studyroom != null) {
            studyroom.setName(updatedStudyRoom.getName());
            studyroomRepo.save(studyroom);
        }
        return "redirect:/studyrooms";
    }

    // Handler method for deleting a study room
    @GetMapping("/delete-studyroom/{id}")
    public String deleteStudyRoom(@PathVariable Long id) {
        studyroomRepo.deleteById(id);
        return "redirect:/studyrooms";
    }
}
