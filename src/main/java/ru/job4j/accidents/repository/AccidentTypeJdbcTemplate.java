package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplate {
    private final JdbcTemplate jdbc;

    public List<AccidentType> findAll() {
        return jdbc.query("SELECT id, type_name FROM types",
                (rs, rowNum) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("type_name"));
                    return type;
                });
    }

    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject(
                "SELECT id, type_name "
                        + "FROM types "
                        + "WHERE id = ?",
                (rs, rowNum) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("type_name"));
                    return type;
                },
                id
        ));
    }
}
