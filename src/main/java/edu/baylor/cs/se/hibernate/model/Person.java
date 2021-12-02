package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Person implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "personId", nullable = false)
    private Long personId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @Column(name = "DOB")
    private Date DOB;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "university")
    private String university;

    @ManyToMany
    @JoinColumn(name = "contestId", referencedColumnName = "contestId")
    private Set<Contest> manageableContest;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team memberJoinedInTeams;

    @Column(name = "personType")
    private final Type type;
    public static enum Type {
        CONTESTANT, COACH
    }

    public Person() {
        type = null;
    }



}