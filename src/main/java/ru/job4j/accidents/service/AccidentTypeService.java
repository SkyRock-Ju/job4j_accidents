package ru.job4j.accidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentTypeService {

    @Autowired
    private AccidentTypeRepository typeRepository;

    public List<AccidentType> findAll() {
        return typeRepository.findAll();
    }

    public Optional<AccidentType> findById(int id) {
        return typeRepository.findById(id);
    }

}
