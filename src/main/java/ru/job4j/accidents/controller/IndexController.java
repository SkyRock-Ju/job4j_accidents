package ru.job4j.accidents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

@Controller
public class IndexController {

    @Autowired
    AccidentService accidentService;

    @GetMapping("/")
    public String index(Model model) {
        Accident accident = new Accident();
        accident.setName("accident without injuries");
        accident.setText("2 small cars");
        accident.setAddress("test street 1");
        accidentService.save(accident);
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }

}
