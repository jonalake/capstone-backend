package com.jonalake.birdtrackerapi.bird;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BirdService {
  @Autowired
  private BirdRepository birdRepository;

  public Iterable<Bird> list() {
    return birdRepository.findAll();
  }

  public Optional<Bird> findById(Long id) {
    return birdRepository.findById(id);
  }

  public Bird create(Bird bird) {
    return birdRepository.save(bird);
  }

  public Optional<Bird> update(Bird bird) {
    Optional<Bird> foundBird = birdRepository.findById(bird.getId());

    if (foundBird.isPresent()) {
      Bird updatedBird = foundBird.get();
      updatedBird.setName(bird.getName());
      updatedBird.setQuantity(bird.getQuantity());
      updatedBird.setLocation(bird.getLocation());
      updatedBird.setDate(bird.getDate());

      birdRepository.save(updatedBird);
      return Optional.of(updatedBird);
    } else {
      return Optional.empty();
    }
  }

  public void deleteById(Long id) {
    birdRepository.deleteById(id);
  }
}