package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplate {

    private final JdbcTemplate jdbc;

    public List<Rule> findAll() {
        return jdbc.query("SELECT id, rule_name FROM rules",
                (rs, rowNum) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("rule_name"));
                    return rule;
                });
    }

    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(
                jdbc.queryForObject(
                        "SELECT id, rule_name "
                                + "FROM rules "
                                + "WHERE id = ?",
                        (rs, rowNum) -> {
                            Rule rule = new Rule();
                            rule.setId(rs.getInt("id"));
                            rule.setName(rs.getString("rule_name"));
                            return rule;
                        },
                        id
                ));
    }

    public Set<Rule> findByIds(List<Integer> ids) {
        return ids.stream()
                .map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::orElseThrow)
                .collect(Collectors.toSet());
    }
}
