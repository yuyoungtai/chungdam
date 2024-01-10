package com.theclass.service;

import com.theclass.domain.event.Corp;
import com.theclass.domain.event.CorpRepository;
import com.theclass.web.dto.CorpDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CorpService {
    private final CorpRepository repository;

    @Transactional
    public Long save(CorpDto dto){
        return repository.save(dto.toEntity()).getCorpId();
    }

    @Transactional
    public CorpDto findCorpsByCorpId(Long corpId){
        return repository.findCorpsByCorpId(corpId);
    }

    @Transactional
    public List<CorpDto> findCorpsByEmail(String email){
        return repository.findCorpsByEmail(email);
    }

    @Transactional
    public List<CorpDto> findCorpsByCorpContaining(String corp){
        return repository.findCorpsByCorpContaining(corp);
    }

    @Transactional
    public List<CorpDto> findCorpsByGuestHp(String guestHp){
        return repository.findCorpsByGuestHp(guestHp);
    }

    @Transactional
    public void update(CorpDto dto){
        Corp entity = repository.findById(dto.getCorpId()).get();
        entity.update(dto);
    }
}
