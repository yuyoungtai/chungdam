package com.theclass.domain.contract;

import com.theclass.web.dto.ContractDto;
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
@Table(name="contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cont_id")
    private Long contId;

    @Column(name="event_id", nullable = false, columnDefinition = "BIGINT(11)")
    private Long eventId;

    @Column(name = "prod_title", columnDefinition = "VARCHAR(50)")
    private String prodTitle;

    @Column(name = "apply_price", nullable = false, columnDefinition = "INT(10) default 0")
    private int applyPrice;

    @Column(nullable = false, columnDefinition = "INT(10) default 0")
    private int count;

    @Column(nullable = false, columnDefinition = "INT(10) default 0")
    private int total;

    @Column(columnDefinition = "VARCHAR(11)")
    private String cancel;

    @Column(name = "create_date", columnDefinition = "VARCHAR(50)")
    private String createDate;

    @Builder
    public Contract(Long contId, Long eventId, String prodTitle, int applyPrice, int count, int total, String cancel, String createDate){
        this.contId = contId;
        this.eventId = eventId;
        this.prodTitle = prodTitle;
        this.applyPrice = applyPrice;
        this.count = count;
        this.total = total;
        this.cancel = cancel;
        this.createDate = createDate;
    }

    public void update(ContractDto dto){
        this.contId = dto.getContId();
        this.eventId = dto.getEventId();
        this.prodTitle = dto.getProdTitle();
        this.applyPrice = dto.getApplyPrice();
        this.count = dto.getCount();
        this.total = dto.getTotal();
        this.cancel = dto.getCancel();
        this.createDate = dto.getCreateDate();
    }
}
