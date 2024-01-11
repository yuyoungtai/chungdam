package com.theclass.domain.event;

import com.theclass.web.dto.CorpDto;
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
@Table(name="corp")
public class Corp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="corp_id")
    private Long corpId;

    @Column(columnDefinition = "VARCHAR(150)")
    private String corp;

    @Column(name = "corp_title", columnDefinition = "VARCHAR(255)")
    private String corpTitle;

    @Column(name = "event_date", columnDefinition = "VARCHAR(11)")
    private String eventDate;

    @Column(name = "event_time", columnDefinition = "VARCHAR(11)")
    private String eventTime;

    @Column(nullable = false, columnDefinition = "INT(10) default 0")
    private int person;

    @Column(columnDefinition = "VARCHAR(20)")
    private String guest;

    @Column(name = "guest_hp", columnDefinition = "VARCHAR(16)")
    private String guestHp;

    @Column(columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(columnDefinition = "VARCHAR(255)")
    private String tex;

    @Column(columnDefinition = "VARCHAR(11)")
    private String cancel;

    @Column(name = "directing_memo", columnDefinition = "TEXT")
    private String directingMemo;

    @Column(name = "flower_memo", columnDefinition = "TEXT")
    private String flowerMemo;

    @Column(name = "food_memo", columnDefinition = "TEXT")
    private String foodMemo;

    @Column(name = "create_date", columnDefinition = "VARCHAR(11)")
    private String createDate;

    @Builder
    public Corp(Long corpId, String corp, String corpTitle, String eventDate, String eventTime, int person, String guest,
                String guestHp, String email, String tex, String cancel, String directingMemo, String flowerMemo, String foodMemo, String createDate){
        this.corpId = corpId;
        this.corp = corp;
        this.corpTitle = corpTitle;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.person = person;
        this.guest = guest;
        this.guestHp = guestHp;
        this.email = email;
        this.tex = tex;
        this.cancel = cancel;
        this.directingMemo = directingMemo;
        this.flowerMemo = flowerMemo;
        this.foodMemo = foodMemo;
        this.createDate = createDate;
    }

    public void update(CorpDto dto){
        this.corpId = dto.getCorpId();
        this.corp = dto.getCorp();
        this.corpTitle = dto.getCorpTitle();
        this.eventDate = dto.getEventDate();
        this.eventTime = dto.getEventTime();
        this.person = dto.getPerson();
        this.guest = dto.getGuest();
        this.guestHp = dto.getGuestHp();
        this.email = dto.getEmail();
        this.tex = dto.getTex();
        this.directingMemo = dto.getDirectingMemo();
        this.flowerMemo = dto.getFlowerMemo();
        this.foodMemo = dto.getFoodMemo();
    }

}
