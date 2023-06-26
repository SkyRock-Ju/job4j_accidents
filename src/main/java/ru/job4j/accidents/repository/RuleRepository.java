package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//@Repository
public class RuleRepository {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    public RuleRepository() {
        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }

    public List<Rule> findAll() {
        return rules.values().stream().toList();
    }

    public Optional<Rule> findById(int id) {
        return Optional.of(rules.get(id));
    }

    public List<Rule> findByIds(List<Integer> ids) {
        List<Rule> result = new ArrayList<>();
        ids.forEach(id -> {
            if (rules.containsKey(id)) {
                result.add(rules.get(id));
            }
        });
        return result;
    }

}
