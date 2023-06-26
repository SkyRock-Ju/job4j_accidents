package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.UserService;

@Controller
@AllArgsConstructor
public class RegController {
    private final UserService userService;

    @GetMapping("/reg")
    public String regPage(@RequestParam(value = "error", required = false) String error,
                          Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username is already taken. "
                    + "Try another username";
        }
        model.addAttribute("regError", errorMessage);
        return "login/reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        if (userService.save(user).isEmpty()) {
            return "redirect:/reg?error=true";
        }
        return "redirect:/login";
    }
}
