package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.mfu.domain.User;
import th.mfu.repository.UserRepository;

@Controller
public class UserController {

    // Injecting UserRepository dependency using @Autowired
    @Autowired
    UserRepository userrepo;

    // Constructor for dependency injection
    public UserController(UserRepository userrepo) {
        this.userrepo = userrepo;
    }

    // Handler method for displaying the home page
    @GetMapping("/home")
    public String HomePage() {
        return "home";
    }

    // Handler method for displaying the help page
    @GetMapping("/help")
    public String HelpPage() {
        return "Help";
    }

    // Handler method for displaying the signup form
    @GetMapping("/signup")
    public String SingupForm(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    // Handler method for saving a new user account
    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute User user, Model model) {
        userrepo.save(user);
        // Retrieves all users from the repository and adds them to the model
        Iterable<User> userlist = userrepo.findAll();
        model.addAttribute("user", userlist);
        return "home";
    }

    // Handler method for displaying the login form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "Login";
    }

    // Handler method for processing user login
    @PostMapping("/login")
    public String userLogin(@ModelAttribute User user, Model model) {
        // Checks if a user with the provided email exists in the repository
        if (userrepo.existsByEmail(user.getEmail()) == true) {
            // Retrieves the existing user with the provided email
            User existUser = userrepo.findByEmail(user.getEmail());
            // Compares the provided password with the stored password
            if (user.getPassword().equals(existUser.getPassword())) {
                // If passwords match, redirect to the "Dashboard" view
                return "Dashboard";
            } else {
                // If passwords do not match, return to the "Login" view
                return "Login";
            }
        } else {
            // If no user with the provided email, return to the "Login" view
            return "Login";
        }
    }
}
