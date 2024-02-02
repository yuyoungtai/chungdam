package com.theclass.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SmsDto {
    private Long id;
    private String msg;
    private String manTel;
    private String womanTel;
    private String type;

    @Builder
    public SmsDto(Long id, String mantel, String womanTel, String msg, String type){
        this.id = id;
        this.msg = msg;
        this.manTel = mantel;
        this.womanTel = womanTel;
        this.type = type;
    }

}
