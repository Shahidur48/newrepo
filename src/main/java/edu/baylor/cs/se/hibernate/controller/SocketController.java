package edu.baylor.cs.se.hibernate.controller;


import edu.baylor.cs.se.hibernate.model.*;
import edu.baylor.cs.se.hibernate.services.ContestService;
import edu.baylor.cs.se.hibernate.services.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SocketController {

    private JmsProducer jmsProducer;
    private ContestService contestService;

    @Autowired
    public SocketController(ContestService contestService, JmsProducer jmsProducer)
    {
        this.contestService = contestService;
        this.jmsProducer = jmsProducer;
    }

    @MessageMapping("/user")
    @SendTo("/topic/user")
    public UserResponse getUser(User user) {

        List<Team> teamList = new ArrayList<>();
        List<Contest> contests = new ArrayList<>();
        String name = null;
        String teamState = null;
        String rank = null;
        Long teamId = null;
        contests = contestService.allContest();

        for(Contest contest: contests){
            Contest contest1 = contests.get(1);
            if(contests.get(1).getContestName().equals("ACM Regional")){
                teamList = contest1.getTeams();
                Team team = teamList.get(0);

                name = team.getTeamName();
                teamState = team.getTeamState();
                rank = team.getRank().toString();
            }
        }

        return new UserResponse(name, teamState, rank);
    }

    @MessageMapping("/updateTeam")
    @SendTo("/topic/user")
    public UserResponse updateTeam(Team team) {

        Team team1 =  contestService.updateTeam(team);
//
        UserResponse userResponse = new UserResponse(team1.getTeamName(), team1.getTeamState(), team1.getRank().toString());
        jmsProducer.sendToJms(userResponse);
        return new UserResponse(team1.getTeamName(), team1.getTeamState(), team1.getRank().toString());
    }

}
