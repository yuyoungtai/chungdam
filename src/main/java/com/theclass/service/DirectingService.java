package com.theclass.service;

import com.theclass.domain.directing.Directing;
import com.theclass.domain.directing.DirectingRepository;
import com.theclass.web.dto.DirectingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DirectingService {
    private final DirectingRepository repository;

    @Transactional
    public DirectingDto findDirectingByEventId(Long eventId){
        return repository.findDirectingByEventId(eventId);
    }

    @Transactional
    public void save(DirectingDto dto){
        repository.save(dto.toEntity());
    }

    @Transactional
    public void update(DirectingDto dto){
        Directing entity = repository.findById(dto.getDirectingId()).get();
        entity.update(dto);
    }
}
