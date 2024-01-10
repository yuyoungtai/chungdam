package com.theclass.service;

import com.theclass.domain.contract.CorpContract;
import com.theclass.domain.contract.CorpContractRepository;
import com.theclass.web.dto.CorpContractDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CorpContractService {
    private final CorpContractRepository repository;

    @Transactional
    public CorpContractDto findCorpContractByContId(Long contId){
        return repository.findCorpContractByContId(contId);
    }

    @Transactional
    public void save(CorpContractDto dto){
        repository.save(dto.toEntity());
    }

    @Transactional
    public void update(CorpContractDto dto){
        CorpContract entity = repository.findById(dto.getContId()).get();
        entity.update(dto);
    }

    @Transactional
    public List<CorpContractDto> findCorpContractsByCorpId(Long corpId){
        return repository.findCorpContractsByCorpId(corpId);
    }
}
