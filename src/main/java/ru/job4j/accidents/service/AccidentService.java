package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentJpaRepository accidentRepository;

    public List<Accident> findAllAccidents() {
        return accidentRepository.findAll();
    }

    public Optional<Accident> findAccidentById(int id) {
        return accidentRepository.findById(id);
    }

    public Accident saveAccident(Accident accident) {
        return accidentRepository.save(accident);
    }

    public Accident updateAccident(Accident accident) {
        if (!accidentRepository.existsById(accident.getId())) {
            throw new IllegalArgumentException("Accident not found");
        }
        return accidentRepository.save(accident);
    }

    public void deleteAccidentById(int id) {
        accidentRepository.deleteById(id);
    }
}
