package com.theclass.domain.event;

import com.theclass.web.dto.CorpDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CorpRepository extends JpaRepository<Corp, Long> {

    CorpDto findCorpsByCorpId(Long corpId);

    List<CorpDto> findCorpsByEmail(String email);

    List<CorpDto> findCorpsByCorpContaining(String corp);

    @Query("SELECT c FROM Corp AS c WHERE c.guestHp LIKE %:guestHp")
    List<CorpDto> findCorpsByGuestHp(String guestHp);
}
