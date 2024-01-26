package com.theclass.service;

import com.theclass.domain.visit.Visit;
import com.theclass.domain.visit.VisitRepository;
import com.theclass.web.dto.VisitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository repository;

    @Transactional
    public void delete(VisitDto dto){
        repository.deleteById(dto.getVisitId());
    }

    @Transactional
    public VisitDto findVisitByVisitId(Long visitId){
        return repository.findVisitByVisitId(visitId);
    }

    @Transactional
    public void save(VisitDto dto){
        repository.save(dto.toEntity());
    }

    @Transactional
    public List<VisitDto> findVisitsByVisitDateGreaterThanEqualOrderByVisitDateAndOrderByVisitTime(String visitDate){
        return repository.findVisitsByVisitDateGreaterThanEqualOrderByVisitDateAndOrderByVisitTime(visitDate);
    }

    @Transactional
    public void update(VisitDto dto){
        Visit entity = repository.findById(dto.getVisitId()).get();
        entity.update(dto);
    }
}
