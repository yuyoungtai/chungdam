package com.theclass.web.dto;

import com.theclass.domain.event.Event;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
        private Long eventId;
        private String eventDate;
        private String eventTime;
        private int person;
        private String groom;
        private String groomHp;
        private String bride;
        private String brideHp;
        private String directingMemo;
        private String flowerMemo;
        private String foodMemo;
        private String cancel;
        private String createDate;
        private String email;

        @Builder
    public EventDto(Event entity){
            this.eventId = entity.getEventId();
            this.eventDate = entity.getEventDate();
            this.eventTime = entity.getEventTime();
            this.email = entity.getEmail();
            this.person = entity.getPerson();
            this.groom = entity.getGroom();
            this.groomHp = entity.getGroomHp();
            this.bride = entity.getBride();
            this.brideHp = entity.getBrideHp();
            this.directingMemo = entity.getDirectingMemo();
            this.flowerMemo = entity.getFlowerMemo();
            this.foodMemo = entity.getFoodMemo();
            this.cancel = entity.getCancel();
            this.createDate = entity.getCreateDate();
        }

        public Event toEntity(){
            return Event.builder()
                    .eventId(eventId)
                    .email(email)
                    .eventDate(eventDate)
                    .eventTime(eventTime)
                    .person(person)
                    .groom(groom)
                    .groomHp(groomHp)
                    .bride(bride)
                    .brideHp(brideHp)
                    .directingMemo(directingMemo)
                    .flowerMemo(flowerMemo)
                    .foodMemo(foodMemo)
                    .cancel(cancel)
                    .createDate(createDate)
                    .build();
        }
}
