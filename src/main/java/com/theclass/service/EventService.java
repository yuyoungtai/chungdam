package com.theclass.service;

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
    public Long save(EventDto dto){
        return repository.save(dto.toEntity()).getEventId();
    }

    @Transactional
    public List<EventDto> findEventsByTel(String tel){
        return repository.findEventsByTel(tel);
    }
}
