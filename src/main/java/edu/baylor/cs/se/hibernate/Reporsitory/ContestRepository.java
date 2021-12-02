package edu.baylor.cs.se.hibernate.Reporsitory;

import edu.baylor.cs.se.hibernate.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, Long> {
}
