package edu.baylor.cs.se.hibernate;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
public class StateType {
  private final State state;

  public static enum State {
    ACCEPTED,PENDING,CANCELED
  }

}
