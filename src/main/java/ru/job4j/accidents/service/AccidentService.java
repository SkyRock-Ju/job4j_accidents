package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentHibernateRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentHibernateRepository accidentRepository;

    public List<Accident> findAllAccidents() {
        return accidentRepository.findAll().stream().toList();
    }

    public Optional<Accident> findAccidentById(int id) {
        return accidentRepository.findById(id);
    }

    public void saveAccident(Accident accident) {
        accidentRepository.save(accident);
    }

    public void updateAccident(Accident accident) {
        accidentRepository.update(accident);
    }

    public void deleteAccidentById(int id) {
        accidentRepository.deleteById(id);
    }
}
