package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping("/createAccident")
    public String viewCreateAccident() {
        return "accident/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidentService.save(accident);
        return "redirect:/";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id).get());
        return "accident/update";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/";
    }
}
