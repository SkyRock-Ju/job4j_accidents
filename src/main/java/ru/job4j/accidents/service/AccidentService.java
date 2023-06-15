package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentJdbcTemplate accidentRepository;

    public List<Accident> findAllAccidents() {
        return accidentRepository.findAll().stream().toList();
    }

    public Optional<Accident> findAccidentById(int id) {
        return accidentRepository.findById(id);
    }

    public Accident saveAccident(Accident accident) {
        return accidentRepository.save(accident);
    }

    public boolean updateAccident(Accident accident) {
        return accidentRepository.update(accident);
    }

    public void deleteAccidentById(int id) {
        accidentRepository.deleteById(id);
    }
}
