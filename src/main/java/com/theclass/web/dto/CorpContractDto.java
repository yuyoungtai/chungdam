package com.theclass.web.dto;

import com.theclass.domain.contract.Contract;
import com.theclass.domain.contract.CorpContract;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CorpContractDto {
    private Long contId;
    private Long corpId;
    private String prodTitle;
    private int applyPrice;
    private int count;
    private int total;
    private String cancel;
    private String createDate;

    @Builder
    public CorpContractDto(CorpContract entity){
        this.contId = entity.getContId();
        this.corpId = entity.getCorpId();
        this.prodTitle = entity.getProdTitle();
        this.applyPrice = entity.getApplyPrice();
        this.count = entity.getCount();
        this.total = entity.getTotal();
        this.cancel = entity.getCancel();
        this.createDate = entity.getCreateDate();
    }

    public CorpContract toEntity(){
        return CorpContract.builder()
                .contId(contId)
                .corpId(corpId)
                .prodTitle(prodTitle)
                .applyPrice(applyPrice)
                .count(count)
                .total(total)
                .cancel(cancel)
                .createDate(createDate)
                .build();
    }
}
