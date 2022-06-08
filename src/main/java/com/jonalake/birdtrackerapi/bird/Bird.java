package com.jonalake.birdtrackerapi.bird;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jonalake.birdtrackerapi.location.Location;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Column;

import lombok.Data;

@Entity
@Data
@Table(name = "bird")
public class Bird {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "quantity")
  private String quantity;

  @ManyToOne
  @JoinColumn(name = "location_id")
  @JsonIgnore
  private Location location;

}