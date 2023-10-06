package com.example.contentcalendar.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContentModel (
  Integer id,
  String title,
  String desc,
  Status status,
  Type contentType,
  LocalDateTime dateCreated,
  LocalDate dateUpdated,
  String url
) {}
