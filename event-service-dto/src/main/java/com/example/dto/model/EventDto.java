package com.example.dto.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto extends RepresentationModel<EventDto> {
  private Long id;
  private String title;
  private String place;
  private String speaker;
  private String eventType;
  private LocalDateTime dateTime;
}
