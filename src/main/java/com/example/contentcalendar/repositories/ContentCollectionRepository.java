package com.example.contentcalendar.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.contentcalendar.models.ContentModel;
import com.example.contentcalendar.models.Status;
import com.example.contentcalendar.models.Type;

import jakarta.annotation.PostConstruct;

@Repository
public class ContentCollectionRepository {
  
  private final List<ContentModel> contentList = new ArrayList<>();

  public ContentCollectionRepository() {}

  public List<ContentModel> findAll() {
    return contentList;
  }

  public Optional<ContentModel> findById(Integer id) {
    return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
  }

  public void save(ContentModel content) {

    contentList.removeIf(c -> c.id().equals(content.id()));

    contentList.add(content);
  }

  public void delete(Integer id) {
    contentList.removeIf(c -> c.id().equals(id));
  }

  // A method that will run automatically
  @PostConstruct
  public void init() {
    ContentModel content = new ContentModel(1,
     "My First Blog Post",
     "My First Blog Post", 
     Status.IDEA, 
     Type.ARTICLE, 
     LocalDateTime.now(), 
     null, 
     "");

    contentList.add(content);
  }
}
