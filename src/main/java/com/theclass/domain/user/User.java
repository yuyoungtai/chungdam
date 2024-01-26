package com.theclass.domain.user;

import com.theclass.web.dto.UserDto;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(columnDefinition = "VARCHAR(20)")
    private String hp;

    @Column(columnDefinition = "VARCHAR(100)")
    private String pass;

    @Builder
    public User(Long userId, String email, String pass, String name, String hp){
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.hp = hp;
        this.pass = pass;
    }

    public void update(UserDto dto){
        this.userId = dto.getUserId();
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.hp = dto.getHp();
        this.pass = dto.getPass();
    }
}
