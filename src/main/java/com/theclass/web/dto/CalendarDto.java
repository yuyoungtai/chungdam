package com.theclass.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CalendarDto implements Comparable<CalendarDto>{
    private Long id;
    private String title;
    private String start;
    private String color;
    private String bgColor;

    @Builder
    public CalendarDto (Long id, String title, String start, String color, String bgColor){
        this.id = id;
        this.title = title;
        this.start = start;
        this.color = color;
        this.bgColor = bgColor;
    }

    @Override
    public int compareTo(CalendarDto o){
        return title.compareTo(o.getTitle());
    }
}
