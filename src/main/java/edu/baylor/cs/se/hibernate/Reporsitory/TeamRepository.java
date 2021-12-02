package edu.baylor.cs.se.hibernate.Reporsitory;

import edu.baylor.cs.se.hibernate.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findTeamByTeamName(String teamName);
}
