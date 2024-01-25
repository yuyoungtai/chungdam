package com.theclass.domain.visit;

import com.theclass.web.dto.VisitDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query("SELECT v FROM Visit AS v WHERE v.visitDate >= :visitDate ORDER BY v.visitDate, v.visitTime ASC")
    List<VisitDto> findVisitsByVisitDateGreaterThanEqualOrderByVisitDateAndOrderByVisitTime(String visitDate);
}
