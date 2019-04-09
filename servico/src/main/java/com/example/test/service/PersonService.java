package com.example.test.service;

import com.example.test.model.Person;
import com.example.test.model.dto.PersonDTO;
import com.example.test.repository.PersonRepository;
import com.example.test.util.MapperUtils;
import com.example.test.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(propagation=Propagation.REQUIRED)
    public Resposta<List<PersonDTO>> list(int offset, int limit) {
        List<Person> personList = personRepository.findAllPaginado(offset, limit);
        Resposta<List<PersonDTO>> resposta = new Resposta<>();
        resposta.setResult(MapperUtils.map(personList, PersonDTO.class));
        resposta.setTotalElements(personRepository.countAll());

        return resposta;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void save(PersonDTO personDTO) {
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        personRepository.save(person);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void edit(PersonDTO personDTO) {
        Person person = personRepository.findById(personDTO.getId()).get();

        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        personRepository.save(person);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void remove(Long id) {
        if (id != null) {
            personRepository.delete(personRepository.findById(id).get());
        }
    }
}
