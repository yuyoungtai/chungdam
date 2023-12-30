package com.theclass.web.dto;

import com.theclass.domain.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String email;
    private String name;
    private String hp;
    private String pass;

    @Builder
    public UserDto(User entity){
        this.userId = entity.getUserId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.hp = entity.getHp();
        this.pass = entity.getPass();
    }

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .hp(hp)
                .pass(pass)
                .build();
    }
}
