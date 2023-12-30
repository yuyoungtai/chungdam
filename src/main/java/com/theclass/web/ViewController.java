package com.theclass.web;

import com.theclass.service.UserService;
import com.theclass.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ViewController {
    private final UserService userService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/loginProcess")
    public String loginProcess(@RequestParam String email, @RequestParam String pass){
        UserDto currentDto = userService.findUserByEmailAndPass(email, pass);

        if(currentDto == null){
            return "failLogin";
        }else{
            return "contractManager";
        }
    }

}
