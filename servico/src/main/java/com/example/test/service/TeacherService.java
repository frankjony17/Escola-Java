package com.example.test.service;

import com.example.test.model.Person;
import com.example.test.model.Student;
import com.example.test.model.Teacher;
import com.example.test.model.dto.PersonDTO;
import com.example.test.model.dto.StudentDTO;
import com.example.test.model.dto.TeacherDTO;
import com.example.test.repository.TeacherRepository;
import com.example.test.util.DateUtils;
import com.example.test.util.MapperUtils;
import com.example.test.util.Resposta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Resposta<List<TeacherDTO>> list(int offset, int limit) {
        List<Teacher> teacherList = teacherRepository.findAllPaginado1(PageRequest.of(offset, limit));
        Resposta<List<TeacherDTO>> resposta = new Resposta<>();

        resposta.setResult(MapperUtils.map(teacherList, TeacherDTO.class));
        resposta.setTotalElements(teacherRepository.countAll());
        return resposta;
    }

//    @Transactional(propagation=Propagation.REQUIRED)
//    public void save(TeacherDTO teacherDTO) {
//        Teacher teacher = new Teacher();
//        teacher.setFirstName(teacherDTO.getFirstName());
//        teacher.setLastName(teacherDTO.getLastName());
//        teacherRepository.save(teacher);
//    }
//
//    @Transactional(propagation=Propagation.REQUIRED)
//    public void edit(TeacherDTO teacherDTO) {
//        Teacher teacher = teacherRepository.findById(teacherDTO.getId()).get();
//
//        teacher.setFirstName(teacherDTO.getFirstName());
//        teacher.setLastName(teacherDTO.getLastName());
//        teacherRepository.save(teacher);
//    }
//
//    @Transactional(propagation=Propagation.REQUIRED)
//    public void remove(Long id) {
//        if (id != null) {
//            teacherRepository.delete(teacherRepository.findById(id).get());
//        }
//    }
}
