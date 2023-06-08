package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccidentRepository {
    Map<Integer, Accident> accidents = new HashMap<>();

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
    }

    public Accident save(Accident accident) {
        return accidents.put(accident.getId(), accident);
    }

    public Accident update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(), (id, mapAccident) -> accidents.put(id, accident));
    }

    public void deleteById(int id) {
        accidents.computeIfPresent(id, (integer, accident) -> accidents.remove(id));
    }
}
