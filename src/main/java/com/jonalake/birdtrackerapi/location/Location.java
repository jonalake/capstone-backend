package com.jonalake.birdtrackerapi.location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

// import com.jonalake.birdtrackerapi.bird.Bird;

import javax.persistence.Id;
// import javax.persistence.OneToMany;

// import java.util.List;

import javax.persistence.Column;

import lombok.Data;

@Entity
@Data
@Table(name = "location")
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private long id;

  @Column(name = "name")
  private String name;

  // @OneToMany(mappedBy = "location")
  // private List<Bird> birds;
}