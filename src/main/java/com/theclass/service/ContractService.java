package com.theclass.service;

import com.theclass.domain.contract.Contract;
import com.theclass.domain.contract.ContractRepository;
import com.theclass.web.dto.ContractDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository repository;

    @Transactional
    public List<ContractDto> findContractByGroupByNoCancel(){
        return repository.findContractByGroupByNoCancel();
    }

    @Transactional
    public ContractDto findContractByContId(Long contId){
        return repository.findContractByContId(contId);
    }

    @Transactional
    public void update(ContractDto dto){
        Contract entity = repository.findById(dto.getContId()).get();
        if(entity != null){
            entity.update(dto);
        }
    }

    @Transactional
    public void save(ContractDto dto){
        repository.save(dto.toEntity());
    }

    @Transactional
    public List<ContractDto> findContractsByEventId(Long eventId){
        return repository.findContractsByEventId(eventId);
    }
}
