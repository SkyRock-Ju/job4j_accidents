package ru.job4j.accidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    public List<Rule> findAll() {
        return ruleRepository.findAll();
    }

    public Optional<Rule> findById(int id) {
        return ruleRepository.findById(id);
    }
}
