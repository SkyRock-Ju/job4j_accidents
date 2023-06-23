package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accidents.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("FROM User "
            + "WHERE username = :username")
    Optional<User> findByName(@Param("username") String name);
}
