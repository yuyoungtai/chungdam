package com.theclass.service;

import com.theclass.domain.user.UserRepository;
import com.theclass.web.dto.UserDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Transactional
    public void save(UserDto dto){
        repository.save(dto.toEntity());
    }

    @Transactional
    public UserDto findUserByEmail(String email){
        return repository.findUserByEmail(email);
    }

    @Transactional
    public UserDto findUserByHp(String hp){
        return repository.findUserByHp(hp);
    }

    @Transactional
    public UserDto findUserByEmailAndPass(String email, String pass){
        return repository.findUserByEmailAndPass(email, pass);
    }
}
