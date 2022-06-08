package com.jonalake.birdtrackerapi.bird;

import java.util.Map;
import java.util.HashMap;

import com.jonalake.birdtrackerapi.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("birds")
public class BirdController {
  @Autowired
  private BirdService birdService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Iterable<Bird>> list() {
    Iterable<Bird> birds = birdService.list();
    return createHashPlural(birds);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Bird> read(@PathVariable Long id) {
    Bird bird = birdService
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No bird with that ID"));
    return createHashSingular(bird);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Bird> create(@Validated @RequestBody Bird bird) {
    Bird createdBird = birdService.create(bird);
    return createHashSingular(createdBird);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Bird> update(@RequestBody Bird bird, @PathVariable Long id) {
    Bird updatedBird = birdService
        .update(bird)
        .orElseThrow(() -> new ResourceNotFoundException("No bird with that ID"));

    return createHashSingular(updatedBird);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    birdService.deleteById(id);
  }

  private Map<String, Bird> createHashSingular(Bird bird) {
    Map<String, Bird> response = new HashMap<String, Bird>();
    response.put("bird", bird);

    return response;
  }

  private Map<String, Iterable<Bird>> createHashPlural(Iterable<Bird> birds) {
    Map<String, Iterable<Bird>> response = new HashMap<String, Iterable<Bird>>();
    response.put("birds", birds);

    return response;
  }
}