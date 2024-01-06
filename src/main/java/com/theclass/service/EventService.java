package com.theclass.service;

import com.theclass.domain.event.Event;
import com.theclass.domain.event.EventRepository;
import com.theclass.web.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository repository;

    @Transactional
    public void update(EventDto dto){
        Event entity = repository.findById(dto.getEventId()).get();
        entity.update(dto);
    }

    @Transactional
    public List<EventDto> findEventsByEmail(String email){
        return repository.findEventsByEmail(email);
    }

    @Transactional
    public List<EventDto> findEventsByHp(String hp){
        return repository.findEventsByHp(hp);
    }

    @Transactional
    public List<EventDto> findEventsByName(String name){
        return repository.findEventsByName(name);
    }

    @Transactional
    public EventDto findEventByEventId(Long eventId){
        return repository.findEventByEventId(eventId);
    }

    @Transactional
    public Long save(EventDto dto){
        return repository.save(dto.toEntity()).getEventId();
    }

    @Transactional
    public List<EventDto> findEventsByTel(String tel){
        return repository.findEventsByTel(tel);
    }
}
