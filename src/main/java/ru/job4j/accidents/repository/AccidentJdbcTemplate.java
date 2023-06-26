package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.util.AccidentRowMapper;

import java.sql.PreparedStatement;
import java.util.*;

//@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Collection<Accident> findAll() {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        jdbc.query(
                    "SELECT * FROM accidents AS ac "
                        + "LEFT JOIN types AS t ON ac.type_id = t.id "
                        + "LEFT JOIN accidents_rules AS ar ON ac.id = ar.accident_id "
                        + "LEFT JOIN rules AS r ON ar.rule_id = r.id",
                new AccidentRowMapper(accidentMap)
        );
        return accidentMap.values();
    }

    public Optional<Accident> findById(int id) {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        jdbc.query(
                    "SELECT * FROM accidents AS ac "
                        + "LEFT JOIN types AS t ON ac.type_id = t.id "
                        + "LEFT JOIN accidents_rules AS ar ON ac.id = ar.accident_id "
                        + "LEFT JOIN rules AS r ON ar.rule_id = r.id "
                        + "WHERE ac.id = ?",
                new AccidentRowMapper(accidentMap), id);
        return Optional.ofNullable(accidentMap.get(id));
    }

    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String INSERT_SQL = "INSERT INTO accidents (name, text, address, type_id) VALUES (?, ?, ?, ?)";
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        updateAccidentRule(accident);
        return accident;
    }

    private void updateAccidentRule(Accident accident) {
        accident.getRules().forEach(rule -> {
                    jdbc.update("INSERT INTO accidents_rules (accident_id, rule_id) VALUES (?, ?)",
                            accident.getId(),
                            rule.getId());
                }
        );
    }

    private void deleteAccidentRule(int id) {
        jdbc.update(
                    "DELETE FROM accidents_rules AS ar "
                        + "WHERE ar.accident_id = ?", id);
    }

    public boolean update(Accident accident) {
        int result = jdbc.update(
                    "UPDATE accidents "
                        + "SET name = ?, text = ?, address = ?, type_id = ? "
                        + "WHERE id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        if (result > 0) {
            deleteAccidentRule(accident.getId());
            updateAccidentRule(accident);
        }
        return result > 0;
    }

    public boolean deleteById(int id) {
        deleteAccidentRule(id);
        int result = jdbc.update(
                  "DELETE FROM accidents AS ac "
                      + "WHERE ac.id = ?", id);
        return result > 0;
    }
}