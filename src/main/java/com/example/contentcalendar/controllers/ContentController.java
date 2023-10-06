package com.example.contentcalendar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.contentcalendar.models.ContentModel;
import com.example.contentcalendar.repositories.ContentCollectionRepository;

@RestController
@RequestMapping("/api/content")
public class ContentController {
  
  private final ContentCollectionRepository repository;

  public ContentController(ContentCollectionRepository repository) {
    this.repository = repository;
  }

  @GetMapping("")
  public List<ContentModel> findAll() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public ContentModel findById(@PathVariable Integer id) {
    return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@RequestBody ContentModel content) {
    repository.save(content);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}")
  public void update(@RequestBody ContentModel content, @PathVariable Integer id) {
    final Optional<ContentModel> oldContent = repository.findById(id);

    if(oldContent.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
    }

    repository.save(content);

  }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
      repository.delete(id);
    }

}
