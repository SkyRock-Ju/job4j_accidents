package ru.job4j.accidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Set<Rule> findByIds(List<Integer> ids) {
        var result = ids.stream().map(id -> findById(id).orElseThrow()).toList();
        return new HashSet<>(result);
    }
}
