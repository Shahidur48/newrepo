package edu.baylor.cs.se.hibernate.controller;

import edu.baylor.cs.se.hibernate.Reporsitory.ContestRepository;
import edu.baylor.cs.se.hibernate.model.Contest;
import edu.baylor.cs.se.hibernate.model.Person;
import edu.baylor.cs.se.hibernate.model.Team;
import edu.baylor.cs.se.hibernate.services.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/contest",                      // <1>
        produces="application/json")
public class ContestController {

    private ContestService contestService;


    @Autowired
    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Contest postContest(@RequestBody Contest contest) {
        return contestService.createContest(contest);
    }

    @GetMapping("/{contestId}")
    public Contest ContestById(@PathVariable("contestId") Long contestId) {
        return contestService.contestById(contestId);
    }

    @GetMapping(produces="application/json")
    public Iterable<Contest> allContest() {
        return contestService.allContest();
    }

    @DeleteMapping("/{contestId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContest(@PathVariable("contestId") Long contestId) {
        contestService.deleteContest(contestId);
    }

    @PutMapping(path="/{contestId}/makeEditable", consumes="application/json")
    public Contest setEditableContest(
            @PathVariable("contestId") Long contestId,
            @RequestBody Contest contest) {
        return contestService.setEditableContest(contestId,contest);
    }


    @PutMapping(path="/{contestId}", consumes="application/json")
    public Contest putContest(
            @PathVariable("contestId") Long contestId,
            @RequestBody Contest contest) {
        return contestService.putContest(contestId,contest);
    }


}
