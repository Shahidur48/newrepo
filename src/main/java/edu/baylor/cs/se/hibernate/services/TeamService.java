package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.Reporsitory.ContestRepository;
import edu.baylor.cs.se.hibernate.Reporsitory.TeamRepository;
import edu.baylor.cs.se.hibernate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
public class TeamService {


    private TeamRepository teamRepository;
    private ContestRepository contestRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, ContestRepository contestRepository) {
        this.teamRepository = teamRepository;
        this.contestRepository = contestRepository;
    }

    public Team saveTeam(Team team){
        return teamRepository.save(team);
    }

    public Team teamById(@PathVariable("id") Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()) {
            return optionalTeam.get();
        }
        return null;
    }

    public List<Team> allTeam() {
        return teamRepository.findAll();
    }

    public void deleteTeam(@PathVariable("teamId") Long teamId) {
        try {
            teamRepository.deleteById(teamId);
        } catch (EmptyResultDataAccessException e) {}
    }

    public Team putTeam(
            @PathVariable("teamId") Long teamId,
            @RequestBody Team team) {
        team.setTeamId(teamId);
        return teamRepository.save(team);
    }

    public Team setEditableTeam(@PathVariable("teamId") Long teamId, @RequestBody Team team)
    {
        Team tm = new Team();
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isPresent()) {
            Team team1 =  optionalTeam.get();
            if(!team1.isEditable())
            {
                team.setTeamId(teamId);
                team.setEditable(true);
                tm = teamRepository.save(team);
            }
            else {
                return null;
            }
        }
        return tm;
    }
    public Team setReadOnlyTeam(@PathVariable("teamId") Long teamId, @RequestBody Team team)
    {
        Team tm = new Team();
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isPresent()) {
            Team team1 =  optionalTeam.get();
            if(team1.isEditable())
            {
                team.setTeamId(teamId);
                team.setEditable(false);
                tm = teamRepository.save(team);
            }
            else {
                return null;
            }
        }
        return tm;
    }

    public Team contestRegistration(ContestTeamDTO contestTeamDTO) {
        Team saveTeam = new Team();
        Team team = contestTeamDTO.getTeam();
        Long contestId = contestTeamDTO.getContestId();
        Integer contestantCounter = 0;
        Integer coachCounter = 0;
        Integer age = 0;
        Integer ageCounter = 0;
        Optional<Contest> optionalContest = contestRepository.findById(contestId);
        if (optionalContest.isPresent()) {

            System.out.println("FSDFSAFSA"+ optionalContest.get().getContestName());
            Integer capacity = optionalContest.get().getCapacity();
            System.out.println("Capacity    " +capacity);
            Set<Person> contestant = team.getTeamMember();
            if(capacity > optionalContest.get().getTeams().size())
            {
                    for(Person member: contestant)
                    {
                        System.out.println("FSDFSAFSA"+ optionalContest.get().getContestName());
                       // String findCoach = member.getType().toString();

                        if(member.getType().toString().equals("CONTESTANT"))
                        {
                            Date sDOB = member.getDOB();
                            Period period = Period.between(sDOB.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
                            age = period.getYears();
                            //System.out.println("FSDFSAFSA"+ optionalContest.get().getContestName());
                            if(age < 24){
                                ageCounter++;
                            }
                            contestantCounter++;
                        }
                        else {
                           // System.out.println("final"+ optionalContest.get().getContestName());
                            coachCounter++;
                        }
                    }

                    if(coachCounter == 1 && contestantCounter == 3 && ageCounter == 3)
                    {
                        team.setContest(optionalContest.get());
                        saveTeam = teamRepository.save(team);
                        Contest contest = optionalContest.get();
                        List<Team> lists = new ArrayList<>();
                        lists.add(team);
                        contest.setTeams(lists);
                        Contest saveContest = contestRepository.save(contest);
                    }


            }
        }
        return saveTeam;
    }

    public Team setPromoteTeam(@PathVariable("teamId") Long teamId,
                               @RequestBody PromotionDTO promotionDTO) {

        Long teamId1 = promotionDTO.getTeamId();
        Long contestId = promotionDTO.getContestId();
        Optional<Team> optionalTeam = teamRepository.findById(teamId1);

        Team saveTeam = new Team();

        if(optionalTeam.isPresent())
        {
            Team tm = optionalTeam.get();
            if(tm.getRank() < 6)
            {
                Optional<Contest> optionalContest = contestRepository.findById(contestId);
                if(optionalContest.isPresent())
                {
                    Contest con = optionalContest.get();
                    String contestType = con.getContestType().toString();
                    if(contestType.equals("REGIONAL"))
                    {
                        Contest newContest = con.getParentContest();
                        tm.setContest(newContest);
                        saveTeam = teamRepository.save(tm);
                    }

                }
            }
        }
        return saveTeam;
    }
}
