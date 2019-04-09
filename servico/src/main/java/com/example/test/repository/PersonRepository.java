package com.example.test.repository;

import com.example.test.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value="SELECT * FROM person LIMIT :limit OFFSET :offset", nativeQuery=true)
    List<Person> findAllPaginado(@Param("offset") Integer offset, @Param("limit") Integer limit);

    @Query(value = "SELECT COUNT(pessoa) FROM Person pessoa")
    Long countAll();
}
