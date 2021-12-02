package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "contestId")
public class Contest implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "contestId", nullable = false)
    private Long contestId;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "contestDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date contestDate;

    @Column(name = "contestName", unique = true)
    private String contestName;

    @Column(name = "registration_allowed")
    private boolean registration_allowed;

    @Column(name = "registration_from")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date registration_from;

    @Column(name = "registration_to")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date registration_to;

    @Column(name = "contestType")
    private Type contestType;
    public static enum Type {
        WORLDFINALS, REGIONAL
    }

    @Column(unique=true)
    private String shortName;

    @Column(name = "isWritableContest")
    private boolean editable;

    public Contest(){
        contestType = null;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    private Contest parentContest;

    @OneToMany(mappedBy = "contest", fetch = FetchType.LAZY)
    private List<Team> teams;

    @ManyToMany(targetEntity =Person.class, mappedBy = "manageableContest",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Person> contestManager;

    public void setContestType(Type input) {
        this.contestType = input;
    }



}