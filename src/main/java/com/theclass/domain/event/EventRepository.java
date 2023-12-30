package com.theclass.domain.event;

import com.theclass.web.dto.EventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event as e where e.groomHp = :tel OR e.brideHp = :tel")
    List<EventDto> findEventsByTel(String tel);
}
