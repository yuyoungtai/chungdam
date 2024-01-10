package com.theclass.web.dto;

import com.theclass.domain.directing.Directing;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectingDto {
    private Long directingId;
    private String email;
    private String plan;
    private String siksun;
    private String mc;
    private String honin;
    private String sunghon;
    private String etc;

    @Builder
    public DirectingDto(Directing entity){
        this.directingId = entity.getDirectingId();
        this.email = entity.getEmail();
        this.plan = entity.getPlan();
        this.siksun = entity.getSiksun();
        this.mc = entity.getMc();
        this.sunghon = entity.getSunghon();
        this.honin = entity.getHonin();
        this.etc = entity.getEtc();
    }

    public Directing toEntity(){
        return Directing.builder()
                .directingId(directingId)
                .email(email)
                .plan(plan)
                .siksun(siksun)
                .mc(mc)
                .honin(honin)
                .sunghon(sunghon)
                .etc(etc)
                .build();
    }
}
