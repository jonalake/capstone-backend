package com.jonalake.birdtrackerapi.bird;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;

import javax.persistence.Column;

import lombok.Data;

@Entity
@Data
@Table(name = "bird")
public class Bird {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "quantity")
  private short quantity;

  @Column(name = "location")
  private String location;

  @Column(name = "date")
  private String date;
}