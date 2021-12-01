package com.example.impl.mapper;

import com.example.dto.model.EventDto;
import com.example.dto.model.Event;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
  public EventDto toDto(Event event) {
    return EventDto.builder()
        .id(event.getId())
        .title(event.getTitle())
        .eventType(event.getEventType())
        .dateTime(event.getDateTime())
        .place(event.getPlace())
        .speaker(event.getSpeaker())
        .build();
  }

  public List<EventDto> toDtoList(List<Event> events) {
    return events.stream().map(this::toDto).collect(Collectors.toList());
  }
}
