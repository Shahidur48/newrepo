package edu.baylor.cs.se.hibernate;

import edu.baylor.cs.se.hibernate.model.Contest;
import edu.baylor.cs.se.hibernate.model.Person;
import edu.baylor.cs.se.hibernate.model.Team;
import edu.baylor.cs.se.hibernate.services.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureMockMvc
public class ExampleTest {


//    public void populate(){
//        Person p1 = new Person();
//        p1.setDOB(new Date("11/12/1990"));
//        p1.setEmail("sr.himel5@gmaail.com");
//        p1.setName("Bob");
//        p1.setUniversity("Baylor University");
//
//        Person p2 = new Person();
//        p2.setDOB(new Date("11/12/1992"));
//        p2.setEmail("imon009@gmaail.com");
//        p2.setName("Imon");
//        p2.setUniversity("Baylor University");
//
//        Person p3 = new Person();
//        p3.setDOB(new Date("11/12/1991"));
//        p3.setEmail("ruhul@gmaail.com");
//        p3.setName("Ruhul");
//        p3.setUniversity("Baylor University");
//
//        Person p4 = new Person();
//        p4.setDOB(new Date("11/12/1991"));
//        p4.setEmail("Joe@gmaail.com");
//        p4.setName("Joe");
//        p4.setUniversity("Baylor University");
//
//        Person p5 = new Person();
//        p5.setDOB(new Date("11/12/1991"));
//        p5.setEmail("ruhul@gmaail.com");
//        p5.setName("Chandler");
//        p5.setUniversity("Baylor University");
//
//        Person p6 = new Person();
//        p6.setDOB(new Date("11/12/1996"));
//        p6.setEmail("monica@gmaail.com");
//        p6.setName("Monica");
//        p6.setUniversity("Baylor University");
//
//        Person p7 = new Person();
//        p7.setDOB(new Date("11/12/1991"));
//        p7.setEmail("Rachel@gmaail.com");
//        p7.setName("Rachel");
//        p7.setUniversity("Baylor University");
//
//        Person p8 = new Person();
//        p8.setDOB(new Date("11/12/1980"));
//        p8.setEmail("Paul@gmaail.com");
//        p8.setName("Paul");
//        p8.setUniversity("Baylor University");
//
//        Person coach = new Person();
//        coach.setDOB(new Date("11/12/1988"));
//        coach.setEmail("JohnDoe@gmail.com");
//        coach.setName("John");
//        coach.setUniversity("Baylor University");
//
//
//
//        ArrayList<Person> teamMembers1 = new ArrayList<>();
//        teamMembers1.add(p1);
//        teamMembers1.add(p2);
//        teamMembers1.add(p3);
//
//        ArrayList<Person> teamMembers2 = new ArrayList<>();
//        teamMembers2.add(p4);
//        teamMembers2.add(p5);
//        teamMembers2.add(p6);
//
//        ArrayList<Person> teamMembers3 = new ArrayList<>();
//        teamMembers3.add(p5);
//        teamMembers3.add(p6);
//        teamMembers3.add(p7);
//
//        Team team1 = new Team();
//        team1.setTeamName("CodeHunter_01");
//        team1.setRank(128);
//        team1.setTeamState(String.valueOf(new StateType(StateType.State.ACCEPTED)));
//        team1.setTeamMember(teamMembers1);
//        team1.setCoach(coach);
//        team1.setContest(null);
//
//
//        Team team2 = new Team();
//        team2.setTeamName("CodeHunter_02");
//        team2.setRank(115);
//        team2.setTeamState(String.valueOf(new StateType(StateType.State.ACCEPTED)));
//        team2.setTeamMember(teamMembers2);
//        team2.setCoach(p7);
//        team1.setContest(null);
//
//        Team team3 = new Team();
//        team3.setTeamName("CodeHunter_03");
//        team3.setRank(44);
//        team3.setTeamState(String.valueOf(new StateType(StateType.State.ACCEPTED)));
//        team3.setTeamMember(teamMembers3);
//        team1.setContest(null);
//
//        team3.setCoach(p7);
//
//
//
//        ArrayList<Team> teams = new ArrayList<>();
//        teams.add(team1);
//        teams.add(team2);
//        teams.add(team3);
//
//
//        Contest cont1 = new Contest();
//        cont1.setCapacity(200);
//        cont1.setContestDate(new Date("01/01/2022"));
//        cont1.setContestName("ICPC");
//        cont1.setRegistration_allowed(true);
//        cont1.setTeams(teams);
//        em.persist(cont1);
//
//        team1.setContest(cont1);
//        team2.setContest(cont1);
//        team3.setContest(cont1);
//
//
//
//
//        p1.setMemberJoinedInTeams(Arrays.asList(team1));
//        p2.setMemberJoinedInTeams(Arrays.asList(team1));
//        p3.setMemberJoinedInTeams(Arrays.asList(team1));
//        p4.setMemberJoinedInTeams(Arrays.asList(team2));
//        p5.setMemberJoinedInTeams(Arrays.asList(team2));
//        p6.setMemberJoinedInTeams(Arrays.asList(team3));
//        p7.setManageableContest(Arrays.asList(cont1));
//        coach.setTeamsManagedByCoach(Arrays.asList(team1));
//
//        em.persist(p1);
//        em.persist(p2);
//        em.persist(p3);
//        em.persist(p4);
//        em.persist(p5);
//        em.persist(p6);
//        em.persist(p7);
//        em.persist(coach);
//
//        em.persist(team1);
//        em.persist(team2);
//        em.persist(team3);
//
//
//    }

//

    public List<Team> populate() {

        Team team = new Team();
        team.setTeamState("Accepted");
        team.setTeamName("Code Hunter");
        team.setTeamId(1L);


        Team team1 = new Team();
        team1.setTeamState("Accepted");
        team1.setTeamName("Baylor 1");


        Team team2 = new Team();
        team2.setTeamState("Accepted");
        team2.setTeamName("Baylor 2");

        List<Team> teams = new ArrayList<>();
        teams.add(team);
        teams.add(team1);
        teams.add(team2);

        return teams;

    }

    @Test
    public void teamList(){
       List<Team> teams =  populate();
        System.out.println(teams);
    }


}
