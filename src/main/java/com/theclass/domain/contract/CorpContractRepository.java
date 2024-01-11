package com.theclass.domain.contract;

import com.theclass.web.dto.CorpContractDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CorpContractRepository extends JpaRepository<CorpContract, Long> {

    CorpContractDto findCorpContractByContId(Long contId);

    List<CorpContractDto> findCorpContractsByCorpId(Long corpId);

    @Query("SELECT c FROM CorpContract AS c WHERE c.cancel IS NULL GROUP BY c.corpId")
    List<CorpContractDto> findCorpContractsByNoCancel();
}
