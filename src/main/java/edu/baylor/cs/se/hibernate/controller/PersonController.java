package edu.baylor.cs.se.hibernate.controller;

import edu.baylor.cs.se.hibernate.Reporsitory.PersonRepository;
import edu.baylor.cs.se.hibernate.model.Person;
import edu.baylor.cs.se.hibernate.model.Team;
import edu.baylor.cs.se.hibernate.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/person",                      // <1>
        produces="application/json")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Person postPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @GetMapping("/{id}")
    public Person personById(@PathVariable("id") Long id) {
        return personService.personById(id);
    }

    @GetMapping(produces="application/json")
    public List<Person> allPerson() {
        return personService.allPerson();
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("personId") Long personId) {
        personService.deletePerson(personId);
    }

    @PutMapping(path="/{personId}", consumes="application/json")
    public Person updatePerson(
            @PathVariable("personId") Long personId,
            @RequestBody Person person) {
        return personService.updatePerson(personId,person);
    }




}
