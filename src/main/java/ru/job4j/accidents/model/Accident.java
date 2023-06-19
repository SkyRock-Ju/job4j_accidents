package ru.job4j.accidents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accidents")
public class Accident {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;
    @OneToOne
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "TYPE_ID_FK"))
    private AccidentType type;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "accidents_rules", joinColumns = {
            @JoinColumn(name = "accident_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "rule_id", nullable = false, updatable = false)})
    private Set<Rule> rules = new HashSet<>();

}
