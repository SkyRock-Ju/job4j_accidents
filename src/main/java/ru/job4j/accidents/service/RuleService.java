package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleJpaRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RuleService {
    private final RuleJpaRepository ruleRepository;

    public List<Rule> findAllRules() {
        return ruleRepository.findAll();
    }

    public Optional<Rule> findRuleById(int id) {
        return ruleRepository.findById(id);
    }

    public Set<Rule> findRulesByIds(String[] stringIds) {
        return new HashSet<>(
                ruleRepository.findByIds(
                        Arrays.stream(stringIds)
                                .map(Integer::parseInt)
                                .collect(Collectors.toSet())));
    }
}
