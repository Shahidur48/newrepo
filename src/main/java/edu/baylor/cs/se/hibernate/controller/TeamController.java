package edu.baylor.cs.se.hibernate.controller;

import edu.baylor.cs.se.hibernate.model.*;
import edu.baylor.cs.se.hibernate.services.ContestService;
import edu.baylor.cs.se.hibernate.services.JmsProducer;
import edu.baylor.cs.se.hibernate.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/team",                      // <1>
        produces="application/json")
@CrossOrigin(origins = "*")
public class TeamController {


    private TeamService teamService;
    private JmsProducer jmsProducer;

    @Autowired
    public TeamController(TeamService teamService, JmsProducer jmsProducer) {
        this.teamService = teamService;
        this.jmsProducer = jmsProducer;
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Team createTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);
    }

    @GetMapping("/{id}")
    public Team teamById(@PathVariable("id") Long id) {
        return teamService.teamById(id);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:8080")
    public List<Team> allTeam() {
        return teamService.allTeam();
    }

    @DeleteMapping("/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("teamId") Long teamId) {
        teamService.deleteTeam(teamId);
    }

    @PutMapping(path="/{teamId}", consumes="application/json")
    public Team putTeam(
            @PathVariable("teamId") Long teamId,
            @RequestBody Team team) {
        return teamService.putTeam(teamId,team);
    }

    @PutMapping(path="/{teamId}/makeEditable", consumes="application/json")
    public Team setEditableTeam(
            @PathVariable("teamId") Long teamId,
            @RequestBody Team team) {
        return teamService.setEditableTeam(teamId,team);
    }

    @PutMapping(path="/{teamId}/setReadOnly", consumes="application/json")
    public Team setReadOnly(@PathVariable("teamId") Long teamId,
                               @RequestBody Team team){
        return teamService.setReadOnlyTeam(teamId,team);
    }

    @PostMapping(path="/contestRegistration", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Team contestRegistration(
            @RequestBody ContestTeamDTO contestTeamDTO) {

        return teamService.contestRegistration(contestTeamDTO);
    }

    @PutMapping(path="/{teamId}/promoteTeam", consumes="application/json")
    public Team setPromoteTeam(
            @PathVariable("teamId") Long teamId,
            @RequestBody PromotionDTO promotionDTO) {
        return teamService.setPromoteTeam(teamId,promotionDTO);
    }

//    @PostMapping(path="/jmsMessage", consumes="application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void jmsPosting(
//            @RequestBody JmsDTO jmsDTO) {
//
//        jmsProducer.sendToJms(jmsDTO);
//    }

}
