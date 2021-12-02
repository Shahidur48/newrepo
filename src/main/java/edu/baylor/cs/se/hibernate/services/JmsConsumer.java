package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.Reporsitory.ContestRepository;
import edu.baylor.cs.se.hibernate.Reporsitory.TeamRepository;
import edu.baylor.cs.se.hibernate.model.Contest;
import edu.baylor.cs.se.hibernate.model.JmsDTO;
import edu.baylor.cs.se.hibernate.model.Team;
import edu.baylor.cs.se.hibernate.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JmsConsumer {


    @JmsListener(destination = "userResponse.queue")
    public void receiveJms(UserResponse userResponse) {
        System.out.println("----------------JMS Message: ------------------");
        System.out.println("Team Name: " + userResponse.getContent() + " Team Status: "+ userResponse.getContent2()+
                " Team Rank: " + userResponse.getContent3());
    }


//    private TeamRepository teamRepository;
//    private ContestRepository contestRepository;
//
//    @Autowired
//    public JmsConsumer(TeamRepository teamRepository, ContestRepository contestRepository) {
//        this.teamRepository = teamRepository;
//        this.contestRepository = contestRepository;
//    }
//
//    @JmsListener(destination = "team.queue")
//    public void receiveJms(JmsDTO jmsDTO) {
//        System.out.println("Hello"+jmsDTO.getTeamId());
//        System.out.println("Status"+jmsDTO.getTeamState());
//
//        int pendingTeams=0,pendingTeams2 =0 ;
//        int acceptedTeams=0,acceptedTeams2 = 0;
//        Long teamId = jmsDTO.getTeamId();
//        Optional<Team> optionalTeam = teamRepository.findById(teamId);
//        if (optionalTeam.isPresent()) {
//            Team team1 =  optionalTeam.get();
//            System.out.println("Hello"+ team1.getTeamName());
//            Contest contest = team1.getContest();
//            List<Team> teams = teamRepository.findAll();
//            for (Team team: teams) {
//                    if(teams.size() >= contest.getCapacity()){
//                        if(team.getTeamState().equals("Canceled")){
//                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Overflow Capacity");
//                        }
//                    }
//                    else
//                    {
//                        if(team.getTeamState().equals("canceled")){
//                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Team is Canceled");
//
//                        }
//                        else{
//                            System.out.println("Team State: "+ team.getTeamState());
//
//                            if(team.getTeamState().equals( "Accepted"))
//                            {
//                                acceptedTeams++;
//                            }
//                            else if(team.getTeamState().equals("Pending"))
//                            {
//                                pendingTeams++;
//
//                            }
//                            System.out.println("Contest Capacity: " +contest.getCapacity());
//                            System.out.println("Accepted Team: " +acceptedTeams);
//                            System.out.println("Pending Team: " +pendingTeams);
//
//
//                            if(jmsDTO.getTeamState().equals("Accepted"))
//                            {
//                                if(team.getTeamState().equals( "Pending"))
//                                {
//                                    if (team.isEditable())
//                                    {
//                                        team.setTeamState(jmsDTO.getTeamState());
//                                        teamRepository.save(team);
//                                        acceptedTeams++;
//                                        pendingTeams--;
//                                    }
//                                    else {
//                                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Contest is Read Only");
//
//                                    }
//                                }
//                            }
//                            else{
//                                if(team.getTeamState().equals( "Accepted"))
//                                {
//                                    if (team.isEditable())
//                                    {
//                                        acceptedTeams--;
//                                        pendingTeams++;
//                                        team.setTeamState(jmsDTO.getTeamState());
//                                        teamRepository.save(team);
//                                    }
//                                    else {
//                                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Contest is Read Only");
//                                    }
//                                }
//                            }
//                            System.out.println("Updated Status: "+ team.getTeamState());
//                        }
//
//            }
//            System.out.println("Contest Capacity: " +contest.getCapacity());
//            System.out.println("Accepted Team: " +acceptedTeams);
//            System.out.println("Pending Team: " +pendingTeams);
//
//        }
//    }
//}
}
