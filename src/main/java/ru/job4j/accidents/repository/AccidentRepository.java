package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//@Repository
public class AccidentRepository {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
    }

    public Accident save(Accident accident) {
        accident.setId(nextId.incrementAndGet());
        return accidents.put(accident.getId(), accident);
    }

    public Accident update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(), (id, mapAccident) -> {
            var accidentNew = new Accident();
            accidentNew.setId(accident.getId());
            accidentNew.setName(accident.getName());
            accidentNew.setText(accident.getText());
            accidentNew.setAddress(accident.getAddress());
            accidentNew.setType(accident.getType());
            accidentNew.setRules(accident.getRules());
            return accidentNew;
        });
    }

    public boolean deleteById(int id) {
        return accidents.remove(id) != null;
    }
}
