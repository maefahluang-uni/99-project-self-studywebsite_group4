package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import th.mfu.domain.Calendar;
import th.mfu.repository.CalendarRepository;

@Controller
public class CalendarController {

    @Autowired
    CalendarRepository calendarRepo;

    // Handler method for "/dashboard" endpoint
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "Dashboard";
    }

    // Handler method for "/studyroom" endpoint
    @GetMapping("/studyroom")
    public String showStudyRoom() {
        return "StudyRoomMain";
    }

    // Handler method for "/flashcard" endpoint
    @GetMapping("/flashcard")
    public String showFlashCard() {
        return "flashcardmain";
    }

    // Handler method for saving a new calendar
    @PostMapping("/save-calendars")
    public String saveCalendar(@ModelAttribute Calendar newcalendar) {
        // Save the new note to the repository
        calendarRepo.save(newcalendar);
        return "redirect:/Dashboard";
    }

    // Handler method for displaying the form to add a new calendar
    @GetMapping("/add-calendars")
    public String addCalendar(Model model) {
        // Add a new Note object to the model to be used in the form
        model.addAttribute("calendar", new Calendar());
        return "add-calendars";
    }

    // Handler method for displaying the list of calendar
    @GetMapping("/calendars")
    public String listCalendar(Model model) {
        model.addAttribute("calendars", calendarRepo.findAll());
        return "calendars";
    }

    // Handler method for deleting a calendar
    @GetMapping("/delete-calendars/{id}")
    public String deleteCalendar(@PathVariable Long id) {
        calendarRepo.deleteById(id);
        return "redirect:/calendars";
    }

}
