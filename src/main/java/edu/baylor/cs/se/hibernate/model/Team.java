package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.baylor.cs.se.hibernate.StateType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "teamId")
public class Team implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "teamId", nullable = false)
    private Long teamId;
    @Column(name = "teamName")
    private String teamName;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "teamState")
    private String teamState;

//    @JoinColumn(name = "personId")
    @OneToMany(mappedBy = "memberJoinedInTeams", fetch = FetchType.LAZY)
    private Set<Person> teamMember = new HashSet<>(4);


    @JoinColumn(name = "contestId")
    @ManyToOne
    private Contest contest;

    @Column(name = "editable")
    private boolean editable;
}
