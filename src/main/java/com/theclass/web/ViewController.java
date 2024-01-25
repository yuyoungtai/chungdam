package com.theclass.web;

import com.theclass.service.UserService;
import com.theclass.service.VisitService;
import com.theclass.web.dto.UserDto;
import com.theclass.web.dto.VisitDto;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ViewController {
    private final UserService userService;
    private final VisitService visitService;

    @RequestMapping("/consultingManger")
    public String consultingManger(Model model){

        //기본적으로 오늘 날짜 이후 리스트 출력
        String toDay = LocalDate.now().toString();
        List<VisitDto> result = visitService.findVisitsByVisitDateGreaterThanEqualOrderByVisitDateAndOrderByVisitTime(toDay);

        if(result.size() > 0){
            for(VisitDto imsi : result){
                imsi.setMasterDate(imsi.getMasterDate().substring(5));
            }
        }

        model.addAttribute("list", result);

        return "consultingManager";
    }

    @RequestMapping("/directingManger")
    public String directingManger(){

        return "directingManger";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/contractCorpManager")
    public String contractCorpManager(){
        return  "contractCorpManager";
    }

    @RequestMapping("/contractManager")
    public String contractManager(){
        return  "contractManager";
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
