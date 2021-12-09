package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "phonebook";
    }

    @GetMapping("{id}")
    public String showContact(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("create")
    public String getCreateForm(@ModelAttribute("user") User user) {
        return "create";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "create";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("{id}/edit")
    public String getEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult result,
                             @PathVariable("id") int id) {
        if (result.hasErrors()) {
            return "edit";
        }
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }
}
