package com.example.rest.controller;

import com.example.api.service.EventService;
import com.example.dto.model.Event;
import com.example.dto.model.EventDto;
import com.example.impl.mapper.EventMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Event Controller", description = "API to GET,POST,UPDATE,DELETE event")
public class EventController {

  private final EventMapper eventMapper;
  private final EventService eventService;

  @Operation(
      summary = "Create event and save to event table",
      description = "The method provides an opportunity to add Event to event table.",
      responses = {
        @ApiResponse(responseCode = "201", description = "Success!"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Service Exception")
      })
  @PostMapping("/event/create")
  public ResponseEntity<EventDto> createEvent(@RequestBody EventDto request) {
    try {
      return new ResponseEntity<>(
          eventMapper.toDto(eventService.createEvent(request)), HttpStatus.CREATED);
    } catch (RuntimeException ex) {
      log.error("Error during event creating. Reason={}", ex.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }

  @Operation(
      summary = "Updates event",
      description = "The method provides an opportunity to update Event in event table.",
      responses = {
        @ApiResponse(responseCode = "200", description = "Success!"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Service Exception")
      })
  @PutMapping("/event/update")
  @ResponseStatus(HttpStatus.OK)
  public void updateEvent(@RequestBody EventDto request) {
    try {
      eventService.updateEvent(request);
    } catch (RuntimeException ex) {
      log.error("Error during event updating. Reason={}", ex.getMessage());
    }
  }

  @Operation(
      summary = "Get event from db by id",
      description = "The method provides an opportunity to get Event by id from event table.",
      responses = {
        @ApiResponse(responseCode = "200", description = "Success!"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Service Exception")
      })
  @GetMapping("/event/{id}")
  public ResponseEntity<EventDto> getEvent(@PathVariable Long id) {
    try {
      Optional<Event> optionalEvent = eventService.getEvent(id);
      return optionalEvent
          .map(event -> ResponseEntity.ok(eventMapper.toDto(event)))
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (RuntimeException ex) {
      log.error("Error during event creating. Reason={}", ex.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }

  @Operation(
      summary = "Get all events from event table",
      description = "The method provides an opportunity to get Event by id from Event table.",
      responses = {
        @ApiResponse(responseCode = "200", description = "Success!"),
        @ApiResponse(responseCode = "500", description = "Service Exception")
      })
  @GetMapping("/events")
  public ResponseEntity<List<EventDto>> getAllEvents() {
    try {
      List<Event> events = eventService.getAllEvents();
      if (!events.isEmpty()) {
        return ResponseEntity.ok(eventMapper.toDtoList(events));
      }
      return ResponseEntity.notFound().build();
    } catch (RuntimeException ex) {
      log.error("Error during events searching. Reason={}", ex.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }

  @Operation(
      summary = "Get events from db by title",
      description = "The method provides an opportunity to get events by title from Event table.",
      responses = {
        @ApiResponse(responseCode = "200", description = "Success!"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Service Exception")
      })
  @GetMapping("/events-by-title")
  public ResponseEntity<List<EventDto>> getAllEventsByTitle(@RequestParam String title) {
    try {
      List<Event> events = eventService.getAllEventsByTitle(title);
      if (events.isEmpty()) {
        return ResponseEntity.ok(eventMapper.toDtoList(eventService.getAllEvents()));
      }
      return ResponseEntity.notFound().build();
    } catch (RuntimeException ex) {
      log.error("Error during events searching. Reason={}", ex.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }

  @Operation(
      summary = "Delete event from db by id",
      description = "The method provides an opportunity to delete Event by id from event table.",
      responses = {
        @ApiResponse(responseCode = "200", description = "Success!"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Service Exception")
      })
  @DeleteMapping("/event/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Long id) {
    try {
      eventService.deleteEvent(id);
    } catch (RuntimeException ex) {
      log.error("Error during event deleting. Reason={}", ex.getMessage());
    }
  }
}
