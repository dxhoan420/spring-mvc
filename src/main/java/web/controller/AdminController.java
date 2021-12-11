package web.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleRepository;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/phonebook";
    }

    @GetMapping("user/{id}")
    public String showContact(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/user";
    }

    @GetMapping("create")
    public String getCreateForm(@ModelAttribute("user") User user) {
        return "admin/create";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/create";
        }
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("{id}/edit")
    public String getEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PatchMapping("{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult result,
                             @PathVariable("id") int id) {
        if (result.hasErrors()) {
            return "admin/edit";
        }
        userService.updateUser(id, user);
        return "redirect:/admin/";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }
}
