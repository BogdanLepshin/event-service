package com.example.api.service;

import com.example.dto.model.EventDto;
import com.example.dto.model.Event;
import java.util.List;
import java.util.Optional;

public interface EventService {
  Event createEvent(EventDto event);

  void updateEvent(EventDto event);

  Optional<Event> getEvent(Long id);

  void deleteEvent(Long id);

  List<Event> getAllEvents();

  List<Event> getAllEventsByTitle(String title);
}
