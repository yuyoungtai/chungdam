package com.theclass.web;

import com.theclass.service.EventService;
import com.theclass.service.UserService;
import com.theclass.web.dto.EventDto;
import com.theclass.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class RestController {

    private final UserService userService;
    private final EventService eventService;

    //신규 이벤트 등록
    @RequestMapping("/addNewEvent")
    public ResponseEntity<String> addNewEvent(@RequestBody EventDto reqDto){
        //기존에 동일한 정보가 있는지 체크
        List<EventDto> currentList = eventService.findEventsByTel(reqDto.getGroomHp());
        if(currentList.size() > 0){
            return new ResponseEntity<String>("groomHp", HttpStatus.OK);
        }else{
            currentList = eventService.findEventsByTel(reqDto.getBrideHp());
            if(currentList.size() > 0){
                return new ResponseEntity<String>("brideHp", HttpStatus.OK);
            }
        }

        Long savedId = eventService.save(reqDto);

        return new ResponseEntity<String>(String.valueOf(savedId), HttpStatus.OK);
    }

    //가입 id 찾기
    @RequestMapping("/findSignupEmail")
    public ResponseEntity<String> findSignupEmail(@RequestBody UserDto reqDto){
        UserDto currentDto = userService.findUserByHp(reqDto.getHp());

        if(currentDto == null){
            return new ResponseEntity<String>("noData", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(currentDto.getEmail(), HttpStatus.OK);
        }
    }

    //가입 pw 찾기
    @RequestMapping("/findSignupPass")
    public ResponseEntity<String> findSignupPass(@RequestBody UserDto reqDto){
        UserDto currentDto = userService.findUserByHp(reqDto.getHp());

        if(currentDto == null){
            return new ResponseEntity<String>("noData", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(currentDto.getPass(), HttpStatus.OK);
        }
    }

    @RequestMapping("/signupEmail")
    public ResponseEntity<String> signupEmail(@RequestBody UserDto reqDto){

        //중복된 이메일이 있는지 체크
        UserDto currentDto = userService.findUserByEmail(reqDto.getEmail());
        if(currentDto == null){
            userService.save(reqDto);
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("duplicated", HttpStatus.OK);
        }
    }
}
