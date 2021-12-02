package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.StateType;
import edu.baylor.cs.se.hibernate.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

//Spring annotations, feel free to ignore it
@Repository
@Transactional
public class SuperRepository {

    @PersistenceContext
    private EntityManager em;

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
//        Set<Person> teamMembers1 = new HashSet<>();
//        teamMembers1.add(p1);
//        teamMembers1.add(p2);
//        teamMembers1.add(p3);
//
//        Set<Person> teamMembers2 = new HashSet<>();
//        teamMembers2.add(p4);
//        teamMembers2.add(p5);
//        teamMembers2.add(p6);
//
//        Set<Person> teamMembers3 = new HashSet<>();
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
//        Set<Team> teams = new HashSet<>();
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
//        cont1.setTeams((List<Team>) teams);
//        em.persist(cont1);
//
//        team1.setContest(cont1);
//        team2.setContest(cont1);
//        team3.setContest(cont1);
//
//
//
//
////        p1.setMemberJoinedInTeams(Sets.as(team1));
////        p2.setMemberJoinedInTeams(Arrays.asList(team1));
////        p3.setMemberJoinedInTeams(Arrays.asList(team1));
////        p4.setMemberJoinedInTeams(Arrays.asList(team2));
////        p5.setMemberJoinedInTeams(Arrays.asList(team2));
////        p6.setMemberJoinedInTeams(Arrays.asList(team3));
////        p7.setManageableContest(Arrays.asList(cont1));
////        coach.setTeamsManagedByCoach(Arrays.asList(team1));
//
//        coach.setTeamsManagedByCoach((Set<Team>) team1);
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
//
//
//        /*team1.setContest(cont1);
//        team2.setContest(cont1);
//        team3.setContest(cont1);
//
//
//        ArrayList<Contest> conList = new ArrayList<>();
//        conList.add(cont1);
//
//        p1.setContest(conList);
//        p2.setContest(conList);
//        p3.setContest(conList);
//        p4.setContest(conList);
//        p5.setContest(conList);
//        p6.setContest(conList);
//        p7.setContest(conList);
//        coach.setContest(conList);
//
//        team1.setContest(cont1);
//        team2.setContest(cont1);
//        team3.setContest(cont1);*/
//
//
//
//
//
//
//
//    }

    /**
     * List of courses with more than 2 students (3 and more)
     */
    public List<Team> getAllTeams(){
        return em.createQuery("SELECT t.teamId, t.rank, t.teamName FROM Team t").getResultList();
    }

    public List<Person> getAllPerson(){
        return em.createQuery("SELECT p FROM Person p").getResultList();
    }
}
