package com.theclass.domain.contract;

import com.theclass.web.dto.CorpContractDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorpContractRepository extends JpaRepository<CorpContract, Long> {

    CorpContractDto findCorpContractByContId(Long contId);

    List<CorpContractDto> findCorpContractsByCorpId(Long corpId);
}
