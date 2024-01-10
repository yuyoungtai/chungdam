package com.theclass.domain.directing;

import com.theclass.web.dto.DirectingDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="directing")
public class Directing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="directing_id")
    private Long directingId;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(columnDefinition = "VARCHAR(255)")
    private String plan;

    @Column(columnDefinition = "VARCHAR(255)")
    private String siksun;

    @Column(columnDefinition = "VARCHAR(255)")
    private String mc;

    @Column(columnDefinition = "VARCHAR(255)")
    private String sunghon;

    @Column(columnDefinition = "VARCHAR(255)")
    private String honin;

    @Column(columnDefinition = "VARCHAR(255)")
    private String etc;

    @Builder
    public Directing(Long directingId, String email, String plan, String mc, String siksun, String honin, String sunghon, String etc){
        this.directingId = directingId;
        this.email = email;
        this.plan = plan;
        this.siksun = siksun;
        this.mc = mc;
        this.sunghon = sunghon;
        this.honin = honin;
        this.etc = etc;
    }

    public void update(DirectingDto dto){
        this.directingId = dto.getDirectingId();
        this.email = dto.getEmail();
        this.plan = dto.getPlan();
        this.siksun = dto.getSiksun();
        this.mc = dto.getMc();
        this.honin = dto.getHonin();
        this.sunghon = dto.getSunghon();
        this.etc = dto.getEtc();
    }
}
