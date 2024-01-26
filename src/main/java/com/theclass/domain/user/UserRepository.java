package com.theclass.domain.user;

import com.theclass.web.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDto findUserByEmail(String email);

    UserDto findUserByHp(String hp);

    UserDto findUserByEmailAndPass(String email, String pass);

    List<UserDto> findAllByOrderByUserIdDesc();

    UserDto findUserByUserId(Long userId);
}
