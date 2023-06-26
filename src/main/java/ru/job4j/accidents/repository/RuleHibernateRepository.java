package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.stream.Collectors;

//@Repository
@AllArgsConstructor
public class RuleHibernateRepository {

    private final CrudRepository crudRepository;

    public Collection<Rule> findAll() {
        return crudRepository.query("FROM Rule", Rule.class);
    }

    public Optional<Rule> findById(int id) {
        return crudRepository.optional(
                "FROM Rule WHERE id = :id",
                Rule.class,
                Map.of("id", id));
    }

    public Collection<Rule> findByIds(List<Integer> ids) {
        return ids.stream()
                .map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::orElseThrow)
                .collect(Collectors.toSet());

    }
}
