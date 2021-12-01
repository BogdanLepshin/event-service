package com.example.impl.service;

import com.example.api.service.EventService;
import com.example.dto.model.EventDto;
import com.example.dto.model.Event;
import com.example.impl.repository.EventRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;

  @Override
  public Event createEvent(EventDto event) {
    return eventRepository.save(buildEvent(event));
  }

  private Event buildEvent(EventDto event) {
    return Event.builder()
        .title(event.getTitle())
        .eventType(event.getEventType())
        .dateTime(event.getDateTime())
        .place(event.getPlace())
        .speaker(event.getSpeaker())
        .build();
  }

  @Override
  public void updateEvent(EventDto event) {
    eventRepository.save(buildEvent(event));
  }

  @Override
  public Optional<Event> getEvent(Long id) {
    return eventRepository.findById(id);
  }

  @Override
  public void deleteEvent(Long id) {
    eventRepository.deleteById(id);
  }

  @Override
  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  @Override
  public List<Event> getAllEventsByTitle(String title) {
    return eventRepository.findAllByTitle(title);
  }
}
