package com.theclass.service;

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
    public void save(VisitDto dto){
        repository.save(dto.toEntity());
    }

    @Transactional
    public List<VisitDto> findVisitsByVisitDateGreaterThanEqualOrderByVisitDateAndOrderByVisitTime(String visitDate){
        return repository.findVisitsByVisitDateGreaterThanEqualOrderByVisitDateAndOrderByVisitTime(visitDate);
    }
}
