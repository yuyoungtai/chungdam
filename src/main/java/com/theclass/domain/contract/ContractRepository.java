package com.theclass.domain.contract;

import com.theclass.web.dto.ContractDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    ContractDto findContractByContId(Long contId);

    List<ContractDto> findContractsByEventId(Long eventId);
}
