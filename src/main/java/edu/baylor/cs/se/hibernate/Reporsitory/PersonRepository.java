package edu.baylor.cs.se.hibernate.Reporsitory;

import edu.baylor.cs.se.hibernate.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
