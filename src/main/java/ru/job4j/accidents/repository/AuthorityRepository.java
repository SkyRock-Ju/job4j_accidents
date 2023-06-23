package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    @Query("FROM Authority "
            + "WHERE authority = :name")
    Authority findByName(String name);
}
