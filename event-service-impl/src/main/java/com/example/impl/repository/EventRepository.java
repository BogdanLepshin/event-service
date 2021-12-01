package com.example.impl.repository;

import com.example.dto.model.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
  List<Event> findAllByTitle(String title);
}
