package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernateRepository {

    private final CrudRepository crudRepository;

    public Collection<Accident> findAll() {
        return crudRepository.query(
                    "FROM Accident ac "
                        + "LEFT JOIN FETCH ac.type "
                        + "LEFT JOIN FETCH ac.rules "
                        + "ORDER BY ac.id ASC",
                Accident.class);
    }

    public Optional<Accident> findById(int id) {
        return crudRepository.optional(
                    "FROM Accident ac "
                        + "LEFT JOIN FETCH ac.type "
                        + "LEFT JOIN FETCH ac.rules "
                        + "WHERE ac.id = :id",
                Accident.class,
                Map.of("id", id));
    }

    public void save(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
    }

    public void update(Accident accident) {
        crudRepository.run(session -> session.merge(accident));
    }

    public void deleteById(int id) {
        crudRepository.run("DELETE FROM Accident WHERE id = :id",
                Map.of("id", id));
    }
}
