package com.theclass.domain.event;

import com.theclass.web.dto.EventDto;
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
@Table(name="user")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private Long eventId;

    @Column(name = "event_date", columnDefinition = "VARCHAR(12)")
    private String eventDate;

    @Column(name = "event_time", columnDefinition = "VARCHAR(12)")
    private String eventTime;

    @Column(name = "event_date", nullable = false, columnDefinition = "INT(10) default 0")
    private int person;

    @Column(columnDefinition = "VARCHAR(50)")
    private String groom;

    @Column(name="groom_hp", columnDefinition = "VARCHAR(20)")
    private String groomHp;

    @Column(columnDefinition = "VARCHAR(50)")
    private String bride;

    @Column(name="bride_hp", columnDefinition = "VARCHAR(20)")
    private String brideHp;

    @Column(name="directing_memo", columnDefinition = "TEXT")
    private String directingMemo;

    @Column(name="flower_memo", columnDefinition = "TEXT")
    private String flowerMemo;

    @Column(name="food_memo", columnDefinition = "TEXT")
    private String foodMemo;

    @Column(columnDefinition = "VARCHAR(12)")
    private String cancel;

    @Column(name="create_date", columnDefinition = "VARCHAR(12)")
    private String createDate;

    @Builder
    public Event(Long eventId, String eventDate, String eventTime, int person, String groom, String groomHp, String bride,
                 String brideHp, String directingMemo, String flowerMemo, String foodMemo, String cancel, String createDate){
        this.eventId = eventId;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.person = person;
        this.groom = groom;
        this.groomHp = groomHp;
        this.bride = bride;
        this.brideHp = brideHp;
        this.directingMemo = directingMemo;
        this.flowerMemo = flowerMemo;
        this.foodMemo = foodMemo;
        this.cancel = cancel;
        this.createDate = createDate;
    }

    public void update(EventDto dto){
        this.eventId = dto.getEventId();
        this.eventDate = dto.getEventDate();
        this.eventTime = dto.getEventTime();
        this.person = dto.getPerson();
        this.groom = dto.getGroom();
        this.groomHp = dto.getGroomHp();
        this.bride = dto.getBride();
        this.brideHp = dto.getBrideHp();
        this.directingMemo = dto.getDirectingMemo();
        this.flowerMemo = dto.getFlowerMemo();
        this.foodMemo = dto.getFoodMemo();
        this.cancel = dto.getCancel();
        this.createDate = dto.getCreateDate();
    }

}
