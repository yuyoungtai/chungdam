package com.theclass.domain.user;

import com.theclass.web.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDto findUserByEmail(String email);

    UserDto findUserByHp(String hp);

    UserDto findUserByEmailAndPass(String email, String pass);
}
