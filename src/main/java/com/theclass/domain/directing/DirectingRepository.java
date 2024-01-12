package com.theclass.domain.directing;

import com.theclass.web.dto.DirectingDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectingRepository extends JpaRepository<Directing, Long> {

    DirectingDto findDirectingByEventId(Long eventId);
}
