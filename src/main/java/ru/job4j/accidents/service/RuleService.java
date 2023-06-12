package ru.job4j.accidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.*;

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

    public Set<Rule> findByIds(String[] stringIds) {
        return new HashSet<>(
                ruleRepository.findByIds(
                        Arrays.stream(stringIds).map(Integer::parseInt).toList()));
    }
}
