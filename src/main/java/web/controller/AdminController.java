package web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/")
@AllArgsConstructor
public class AdminController {

    private UserService userService;
    private RoleService roleService;

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
    public String getCreateForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/create";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult result,
                             @RequestParam(value="checked", required = false) long[] checked) {
        if (result.hasErrors()) {
            return "admin/create";
        }
        user.setRoles(roleService.getRolesByIds(checked));
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("{id}/edit")
    public String getEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/edit";
    }

    @PatchMapping("{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult result,
                             @PathVariable("id") int id,
                             @RequestParam(value="checked", required = false) long[] checked) {
        if (result.hasErrors()) {
            return "admin/edit";
        }
        user.setRoles(roleService.getRolesByIds(checked));
        userService.updateUser(id, user);
        return "redirect:/admin/";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }
}
