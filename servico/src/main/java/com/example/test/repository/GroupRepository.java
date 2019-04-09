package com.example.test.repository;

import com.example.test.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(value="SELECT * FROM \"group\" ORDER BY nome ASC LIMIT :limit OFFSET :offset", nativeQuery=true)
    List<Group> findAllPaginado(@Param("offset") Integer offset, @Param("limit") Integer limit);

    @Query(value="SELECT COUNT(grupo) FROM Group grupo")
    Long countAll();
}
