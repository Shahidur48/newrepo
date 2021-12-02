package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.Reporsitory.ContestRepository;
import edu.baylor.cs.se.hibernate.Reporsitory.TeamRepository;
import edu.baylor.cs.se.hibernate.model.Contest;
import edu.baylor.cs.se.hibernate.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ContestService {

    private ContestRepository contestRepository;
    private TeamRepository teamRepository;

    @Autowired
    public ContestService(ContestRepository contestRepository, TeamRepository teamRepository) {
        this.contestRepository = contestRepository;
        this.teamRepository = teamRepository;
    }

    public Contest createContest(Contest contest){
        return contestRepository.save(contest);
    }

    public Contest contestById(@PathVariable("id") Long contestId) {
        Optional<Contest> optionalContest = contestRepository.findById(contestId);
        Contest contest = new Contest();
        if (optionalContest.isPresent()) {
            contest = optionalContest.get();
        }
        return contest;
    }

    public List<Contest> allContest() {
        return contestRepository.findAll();
    }

    public void deleteContest(@PathVariable("teamId") Long teamId) {
        try {
            contestRepository.deleteById(teamId);
        } catch (EmptyResultDataAccessException e) {}
    }

    public Contest putContest(
            @PathVariable("teamId") Long contestId,
            @RequestBody Contest contest) {
        contest.setContestId(contestId);
        return contestRepository.save(contest);
    }

    public Contest setEditableContest(@PathVariable("contestId") Long contestId,
                                      @RequestBody Contest contest) {
        Contest con = new Contest();
        Optional<Contest> optionalContest = contestRepository.findById(contestId);
        if (optionalContest.isPresent()) {
            Contest contest1 =  optionalContest.get();
            if(contest1.isEditable())
            {
                contest.setContestId(contestId);
                con = contestRepository.save(contest);
            }
            else {
                return null;
            }
        }
        return con;
    }

    public Team updateTeam(Team team){
        String teamName = team.getTeamName();
       Team team1 = teamRepository.findTeamByTeamName(teamName);
        //return teamRepository.save(team);
        team1.setTeamState(team.getTeamState());
        System.out.println(team1.getRank());
        System.out.println(team1.getTeamState());

        Team savedTeam = teamRepository.save(team1);

        return savedTeam;
    }
}
