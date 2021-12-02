package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.Reporsitory.ContestRepository;
import edu.baylor.cs.se.hibernate.Reporsitory.PersonRepository;
import edu.baylor.cs.se.hibernate.Reporsitory.TeamRepository;
import edu.baylor.cs.se.hibernate.model.Person;
import edu.baylor.cs.se.hibernate.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person){
        return personRepository.save(person);
    }

    public Person personById(@PathVariable("id") Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            return optionalPerson.get();
        }
        return null;
    }

    public List<Person> allPerson() {
        return personRepository.findAll();
    }

    public Person updatePerson(
            @PathVariable("personId") Long personId,
            @RequestBody Person person) {
        person.setPersonId(personId);
        return personRepository.save(person);
    }

    public void deletePerson(@PathVariable("personId") Long personId) {
        try {
            personRepository.deleteById(personId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
