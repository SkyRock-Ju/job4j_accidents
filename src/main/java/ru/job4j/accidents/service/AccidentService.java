package ru.job4j.accidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {

    @Autowired
    private AccidentRepository accidentRepository;

    public List<Accident> findAll() {
        return accidentRepository.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public Accident save(Accident accident) {
        return accidentRepository.save(accident);
    }

    public Accident update(Accident accident) {
        return accidentRepository.update(accident);
    }

    public boolean deleteById(int id) {
        return accidentRepository.deleteById(id);
    }
}
