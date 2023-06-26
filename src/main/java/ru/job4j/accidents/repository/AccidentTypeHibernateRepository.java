package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

//@Repository
@AllArgsConstructor
public class AccidentTypeHibernateRepository {

    private final CrudRepository crudRepository;

    public Collection<AccidentType> findAll() {
        return crudRepository.query("FROM AccidentType", AccidentType.class);
    }

    public Optional<AccidentType> findById(int id) {
        return crudRepository.optional(
                "FROM AccidentType WHERE id = :id",
                AccidentType.class,
                Map.of("id", id));
    }
}
