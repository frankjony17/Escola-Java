package com.example.test.repository;

import com.example.test.model.Teacher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value="SELECT DISTINCT(teacher) FROM teacher " +
            "inner join person on person.id = teacher.person_id " +
            "left join student_teacher on student_teacher.teacher_id = teacher.id " +
            "left join student on student.id = student_teacher.student_id " +
            "LIMIT :limit OFFSET :offset", nativeQuery=true)
    List<Teacher> findAllPaginado(@Param("offset") Integer offset, @Param("limit") Integer limit);

    @Query(value="SELECT distinct teacher FROM Teacher teacher join fetch teacher.person person left join teacher.students students")
    List<Teacher> findAllPaginado1(Pageable pageable);

    @Query(value = "SELECT COUNT(teacher) FROM Teacher teacher")
    Long countAll();
}
