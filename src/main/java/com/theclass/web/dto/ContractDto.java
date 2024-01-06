package com.theclass.web.dto;

import com.theclass.domain.contract.Contract;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {
    private Long contId;
    private Long eventId;
    private String prodTitle;
    private int applyPrice;
    private int count;
    private int total;
    private String cancel;
    private String createDate;

    @Builder
    public ContractDto(Contract entity){
        this.contId = entity.getContId();
        this.eventId = entity.getEventId();
        this.prodTitle = entity.getProdTitle();
        this.applyPrice = entity.getApplyPrice();
        this.count = entity.getCount();
        this.total = entity.getTotal();
        this.cancel = entity.getCancel();
        this.createDate = entity.getCreateDate();
    }

    public Contract toEntity(){
        return Contract.builder()
                .contId(contId)
                .eventId(eventId)
                .prodTitle(prodTitle)
                .applyPrice(applyPrice)
                .count(count)
                .total(total)
                .cancel(cancel)
                .createDate(createDate)
                .build();
    }
}
