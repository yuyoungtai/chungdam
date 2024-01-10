package com.theclass.domain.contract;

import com.theclass.web.dto.ContractDto;
import com.theclass.web.dto.CorpContractDto;
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
@Table(name="corp_contract")
public class CorpContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cont_id")
    private Long contId;

    @Column(name="corp_id", nullable = false, columnDefinition = "BIGINT(11)")
    private Long corpId;

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
    public CorpContract(Long contId, Long corpId, String prodTitle, int applyPrice, int count, int total, String cancel, String createDate){
        this.contId = contId;
        this.corpId = corpId;
        this.prodTitle = prodTitle;
        this.applyPrice = applyPrice;
        this.count = count;
        this.total = total;
        this.cancel = cancel;
        this.createDate = createDate;
    }

    public void update(CorpContractDto dto){
        this.contId = dto.getContId();
        this.corpId = dto.getCorpId();
        this.prodTitle = dto.getProdTitle();
        this.applyPrice = dto.getApplyPrice();
        this.count = dto.getCount();
        this.total = dto.getTotal();
        this.cancel = dto.getCancel();
        this.createDate = dto.getCreateDate();
    }
}
