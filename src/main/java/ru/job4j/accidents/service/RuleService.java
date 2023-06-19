package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleHibernateRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class RuleService {
    private final RuleHibernateRepository ruleRepository;

    public List<Rule> findAllRules() {
        return ruleRepository.findAll().stream().toList();
    }

    public Optional<Rule> findRuleById(int id) {
        return ruleRepository.findById(id);
    }

    public Set<Rule> findRulesByIds(String[] stringIds) {
        return new HashSet<>(
                ruleRepository.findByIds(
                        Arrays.stream(stringIds).map(Integer::parseInt).toList()));
    }
}
