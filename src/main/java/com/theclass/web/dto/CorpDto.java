package com.theclass.web.dto;

import com.theclass.domain.event.Corp;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CorpDto {
    private Long corpId;
    private String corp;
    private String corpTitle;
    private String eventDate;
    private String eventTime;
    private int person;
    private String guest;
    private String guestHp;
    private String email;
    private String tex;
    private String cancel;
    private String directingMemo;
    private String flowerMemo;
    private String foodMemo;
    private String createDate;
    private String startDate;
    private String endDate;

    @Builder
    public CorpDto(Corp entity){
        this.corpId = entity.getCorpId();
        this.corp = entity.getCorp();
        this.corpTitle = entity.getCorpTitle();
        this.eventDate = entity.getEventDate();
        this.eventTime = entity.getEventTime();
        this.person = entity.getPerson();
        this.guest = entity.getGuest();
        this.guestHp = entity.getGuestHp();
        this.email = entity.getEmail();
        this.tex = entity.getTex();
        this.cancel = entity.getCancel();
        this.directingMemo = entity.getDirectingMemo();
        this.flowerMemo = entity.getFlowerMemo();
        this.foodMemo = entity.getFoodMemo();
        this.createDate = entity.getCreateDate();
    }

    public Corp toEntity(){
        return Corp.builder()
                .corpId(corpId)
                .corp(corp)
                .corpTitle(corpTitle)
                .eventDate(eventDate)
                .eventTime(eventTime)
                .person(person)
                .guest(guest)
                .guestHp(guestHp)
                .email(email)
                .tex(tex)
                .cancel(cancel)
                .directingMemo(directingMemo)
                .flowerMemo(flowerMemo)
                .foodMemo(foodMemo)
                .createDate(createDate)
                .build();
    }
}
