package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeJdbcTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeJdbcTemplate typeRepository;

    public List<AccidentType> findAllTypes() {
        return typeRepository.findAll();
    }

    public Optional<AccidentType> findTypeById(int id) {
        return typeRepository.findById(id);
    }

}
