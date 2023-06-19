package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentJpaRepository extends CrudRepository<Accident, Integer> {

    @Query(   "FROM Accident AS ac "
            + "JOIN FETCH ac.type "
            + "JOIN FETCH ac.rules "
            + "ORDER BY ac.id "
            + "ASC")
    List<Accident> findAll();

    @Query(   "FROM Accident ac "
            + "JOIN FETCH ac.type "
            + "JOIN FETCH ac.rules "
            + "WHERE ac.id = :id")
    Optional<Accident> findById(@Param("id") int id);
}
