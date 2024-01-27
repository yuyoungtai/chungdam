package com.theclass.domain.event;

import com.theclass.web.dto.EventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event AS e WHERE e.eventDate BETWEEN :startDate AND :endDate ORDER BY e.eventDate ASC")
    List<EventDto> findEventsByPeriod(String startDate, String endDate);

    List<EventDto> findEventsByEmail(String email);

    @Query("SELECT e FROM Event AS e WHERE e.groomHp LIKE %:hp OR e.brideHp LIKE %:hp")
    List<EventDto> findEventsByHp(String hp);

    @Query("SELECT e FROM Event AS e WHERE e.groom = : name OR e.bride = :name")
    List<EventDto> findEventsByName(String name);

    @Query("SELECT e FROM Event as e where e.groomHp = :tel OR e.brideHp = :tel")
    List<EventDto> findEventsByTel(String tel);

    EventDto findEventByEventId(Long eventId);
}
