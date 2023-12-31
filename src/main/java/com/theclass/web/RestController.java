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

import java.time.LocalDate;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class RestController {

    private final UserService userService;
    private final EventService eventService;

    //event 이메일 검색
    @RequestMapping("/searchEventByEmail")
    public ResponseEntity<List<EventDto>> searchEventByEmail(@RequestBody EventDto reqDto){
        List<EventDto> result = eventService.findEventsByEmail(reqDto.getGroomHp());

        return new ResponseEntity<List<EventDto>>(result, HttpStatus.OK);
    }

    //event 전화번호 검색
    @RequestMapping("/searchEventByHp")
    public ResponseEntity<List<EventDto>> searchEventByHp(@RequestBody EventDto reqDto){
        List<EventDto> result = eventService.findEventsByHp(reqDto.getGroomHp());

        return new ResponseEntity<List<EventDto>>(result, HttpStatus.OK);
    }

    //Event 이름 검색
    @RequestMapping("/searchEventByName")
    public ResponseEntity<List<EventDto>> searchEventByName(@RequestBody EventDto reqDto){
        List<EventDto> result = eventService.findEventsByName(reqDto.getGroom());

        return new ResponseEntity<List<EventDto>>(result, HttpStatus.OK);
    }

    //eventId로 이벤트 정보가져오기
    @RequestMapping("/findEventByEventId")
    public ResponseEntity<EventDto> findEventByEventId(@RequestBody EventDto reqDto){
        return new ResponseEntity<EventDto>(eventService.findEventByEventId(reqDto.getEventId()), HttpStatus.OK);
    }

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

        //등록일자 설정
        String toDay = LocalDate.now().toString();
        reqDto.setCreateDate(toDay);
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
