package com.theclass.web;

import com.theclass.service.*;
import com.theclass.utill.Message;
import com.theclass.utill.S3Uploader;
import com.theclass.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class RestController {

    private final UserService userService;
    private final EventService eventService;
    private final ContractService contractService;
    private final S3Uploader s3Uploader;
    private final DirectingService directingService;
    private final CorpService corpService;
    private final CorpContractService corpContractService;
    private final VisitService visitService;
    private final Message message;

    //메시지 발송
    @RequestMapping("/sendDM")
    public ResponseEntity<String> sendDM(@RequestBody List<SmsDto> reqDtoList) throws Exception {
        if(reqDtoList.size() > 0){

            String msg = reqDtoList.get(0).getMsg();
            String type = reqDtoList.get(0).getType();
            String receiver = "";

            if(type.equals("corp")){
                //기업행사
                for(SmsDto dto : reqDtoList){
                    receiver += corpService.findCorpsByCorpId(dto.getId()).getGuestHp();
                    receiver += ",";
                }
                //마지막 콤마 제거
                receiver = receiver.substring(0, receiver.length() - 1);
            }else{
                //웨딩 이벤트
                for(SmsDto dto : reqDtoList){
                    receiver += eventService.findEventByEventId(dto.getId()).getBrideHp();
                    receiver +=",";
                    receiver += eventService.findEventByEventId(dto.getId()).getGroomHp();
                    receiver +=",";
                }
                //마지막 콤마 제거
                receiver = receiver.substring(0, receiver.length() - 1);
            }
            message.sendSMS(receiver, msg);
        }

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //기간별 기업 행사 검색
    @RequestMapping("/findCorpByPeriod")
    public ResponseEntity<List<CorpDto>> findCorpByPeriod(@RequestBody CorpDto reqDto){

        List<CorpDto> result = corpService.findCorpsByPeriod(reqDto.getStartDate(), reqDto.getEndDate());

        return new ResponseEntity<List<CorpDto>>(result, HttpStatus.OK);
    }

    //기간별 웨딩 행사 검색
    @RequestMapping("/findWeddingByPeriod")
    public ResponseEntity<List<EventDto>> findWeddingByPeriod(@RequestBody EventDto reqDto){

        List<EventDto> result = eventService.findEventsByPeriod(reqDto.getStartDate(), reqDto.getEndDate());

        return new ResponseEntity<List<EventDto>>(result, HttpStatus.OK);
    }

    @RequestMapping("/findUserInfoById")
    public ResponseEntity<UserDto> findUserInfoById(@RequestBody UserDto reqDto){
        UserDto currentDto = userService.findUserByUserId(reqDto.getUserId());

        return new ResponseEntity<UserDto>(currentDto, HttpStatus.OK);
    }

    @RequestMapping("/delUser")
    public ResponseEntity<String> delUser(@RequestBody UserDto reqDto){
        userService.delete(reqDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping("/updateUserInfo")
    public ResponseEntity<String> updateUserInfo(@RequestBody UserDto reqDto){
        userService.update(reqDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping("/delVisit")
    public ResponseEntity<String> delVisit(@RequestBody VisitDto reqDto){
        visitService.delete(reqDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping("/findVisitInfoById")
    public ResponseEntity<VisitDto> findVisitInfoById(@RequestBody VisitDto reqDto){
        VisitDto currentDto = visitService.findVisitByVisitId(reqDto.getVisitId());

        return new ResponseEntity<VisitDto>(currentDto, HttpStatus.OK);
    }

    @RequestMapping("/updateVisit")
    public ResponseEntity<String> updateVisit(@RequestBody VisitDto reqDto){
        visitService.update(reqDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping("/addVisit")
    public ResponseEntity<String> addVisit(@RequestBody VisitDto reqDto){
        visitService.save(reqDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping("/searchCorpByCorpHp")
    public ResponseEntity<List<CorpDto>> searchCorpByCorpHp(@RequestBody CorpDto reqDto){
        List<CorpDto> resultList = corpService.findCorpsByGuestHp(reqDto.getGuestHp());

        return new ResponseEntity<List<CorpDto>>(resultList, HttpStatus.OK);
    }

    @RequestMapping("/searchCorpByCorpName")
    public ResponseEntity<List<CorpDto>> searchCorpByCorpName(@RequestBody CorpDto reqDto){
        List<CorpDto> resultList = corpService.findCorpsByCorpContaining(reqDto.getCorp());

        return new ResponseEntity<List<CorpDto>>(resultList, HttpStatus.OK);
    }

    @RequestMapping("/searchCorpByEmail")
    public ResponseEntity<List<CorpDto>> searchCorpByEmail(@RequestBody CorpDto reqDto){
        List<CorpDto> resultList = corpService.findCorpsByEmail(reqDto.getEmail());

        return new ResponseEntity<List<CorpDto>>(resultList, HttpStatus.OK);
    }

    @RequestMapping("/findCorpByCorpId")
    public ResponseEntity<CorpDto> findCorpByCorpId(@RequestBody CorpDto reqDto){
        CorpDto currentDto = corpService.findCorpsByCorpId(reqDto.getCorpId());

        return new ResponseEntity<CorpDto>(currentDto, HttpStatus.OK);
    }

    //기업행사 신규 등록
    @RequestMapping("/addNewCorpEvent")
    public ResponseEntity<String> addNewCorpEvent(@RequestBody CorpDto reqDto){

        reqDto.setCreateDate(LocalDate.now().toString());
        Long savedId = corpService.save(reqDto);

        return new ResponseEntity<String>(String.valueOf(savedId), HttpStatus.OK);
    }

    //디렉팅 파일 가져오기
    @RequestMapping("/getDirectingFile")
    public ResponseEntity<DirectingDto> getDirectingFile(@RequestBody DirectingDto reqDto){
        DirectingDto directingDto = directingService.findDirectingByEventId(reqDto.getEventId());

        return new ResponseEntity<DirectingDto>(directingDto, HttpStatus.OK);
    }

    //기업행사-사업자등록증 업로드
    @RequestMapping("/uploadCorpDoc")
    public ResponseEntity<String> uploadCorpDoc(@RequestParam("file_data") MultipartFile file, HttpServletRequest req)throws IOException{
        //업로드 파일 종류
        String corpId = req.getParameter("corpId");

        if (corpId.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            //aws s3 업로드
            //파일 이름 랜덤 생성 후 'corpDoc/{corpId}/랜덤파일네임' 리턴
            String savedFilePath = s3Uploader.upload(file, "corpDoc/" + corpId);

            CorpDto currentCorpDto = corpService.findCorpsByCorpId(Long.valueOf(corpId));

            if(currentCorpDto != null){
                currentCorpDto.setTex(savedFilePath);
                corpService.update(currentCorpDto);
            }
        }

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //디렉팅 파일 업로드
    @RequestMapping("/directingUpload")
    public ResponseEntity<String> directingUpload(@RequestParam("file_data") MultipartFile file, HttpServletRequest req) throws IOException {

        //업로드 파일 종류
        String type = req.getParameter("type");
        //이메일
        String eventId = req.getParameter("eventId");

        if (eventId.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            //aws s3 업로드
            //파일 이름 랜덤 생성 후 'directing/{hostEmail}/랜덤파일네임' 리턴
            String savedFilePath = s3Uploader.upload(file, "directing/" + eventId);

            //directing 테이블 저장.
            //기존 테이블이 있는지 검사.
            DirectingDto currentDirectingDto = directingService.findDirectingByEventId(Long.valueOf(eventId));

            if (currentDirectingDto == null) {
                //신규 저장.
                currentDirectingDto = new DirectingDto();
                currentDirectingDto.setEventId(Long.valueOf(eventId));

                if (type.equals("wplan")) {
                    currentDirectingDto.setWplan(savedFilePath);
                } else if (type.equals("fplan")) {
                    currentDirectingDto.setFplan(savedFilePath);
                } else if (type.equals("cplan")) {
                    currentDirectingDto.setCplan(savedFilePath);
                }else if (type.equals("dplan")) {
                    currentDirectingDto.setDplan(savedFilePath);
                }else if (type.equals("summary")) {
                    currentDirectingDto.setSummary(savedFilePath);
                } else {
                    //bill
                    currentDirectingDto.setBill(savedFilePath);
                }
                //테이블 저장.
                directingService.save(currentDirectingDto);
            } else {
                //기존 정보 업데이트하고 기존 파일은 삭제처리
                if (type.equals("wplan")) {
                    if (currentDirectingDto.getWplan() != null) {
                        s3Uploader.delOneThumb(currentDirectingDto.getWplan());
                    }
                    currentDirectingDto.setWplan(savedFilePath);
                } else if (type.equals("fplan")) {
                    if (currentDirectingDto.getFplan() != null) {
                        s3Uploader.delOneThumb(currentDirectingDto.getFplan());
                    }
                    currentDirectingDto.setFplan(savedFilePath);
                } else if (type.equals("cplan")) {
                    if (currentDirectingDto.getCplan() != null) {
                        s3Uploader.delOneThumb(currentDirectingDto.getCplan());
                    }
                    currentDirectingDto.setCplan(savedFilePath);
                } else if (type.equals("dplan")) {
                    if (currentDirectingDto.getDplan() != null) {
                        s3Uploader.delOneThumb(currentDirectingDto.getDplan());
                    }
                    currentDirectingDto.setDplan(savedFilePath);
                }else if (type.equals("summary")) {
                    if (currentDirectingDto.getSummary() != null) {
                        s3Uploader.delOneThumb(currentDirectingDto.getSummary());
                    }
                    currentDirectingDto.setSummary(savedFilePath);
                } else {
                    if (currentDirectingDto.getBill() != null) {
                        s3Uploader.delOneThumb(currentDirectingDto.getBill());
                    }
                    currentDirectingDto.setBill(savedFilePath);
                }
                directingService.update(currentDirectingDto);
            }
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }

    //디렉팅 캘린더 데이터 가져오기
    @RequestMapping("/getDirectingData")
    public ResponseEntity<List<CalendarDto>> getDirectingData(){

        List<CalendarDto> calendarDtoList = new ArrayList<CalendarDto>();

        //웨딩 계약건 가져오기
        List<ContractDto> contList = contractService.findContractByGroupByNoCancel();

        //기업행사 계약건 가져오기
        List<CorpContractDto> corpContList = corpContractService.findCorpContractsByNoCancel();

        //웨딩 행사 데이터 가공
        if(contList.size() > 0){
            for(ContractDto cont : contList){
                EventDto eventDto = eventService.findEventByEventId(cont.getEventId());
                String foodType = "";
                String foodCount = "";

                List<ContractDto> imsiContList = contractService.findContractsByEventId(cont.getEventId());
                if(imsiContList.size() > 0){
                    //해당 이벤트 계약내용을 검색해서 식사 메뉴 분류
                    for(ContractDto imsiCont : imsiContList){
                        if(imsiCont.getProdTitle().contains("뷔페")){
                            foodType = "뷔페";
                            foodCount = String.valueOf(imsiCont.getCount());
                        }else if(imsiCont.getProdTitle().contains("코스")){
                            foodType = "코스";
                            foodCount = String.valueOf(imsiCont.getCount());
                        }else if(imsiCont.getProdTitle().contains("큐브")){
                            foodType = "큐브";
                            foodCount = String.valueOf(imsiCont.getCount());
                        }
                    }
                }

                if(eventDto != null){
                    CalendarDto calenda = new CalendarDto();

                    calenda.setTitle(eventDto.getEventTime()+"/"+eventDto.getGroom()+"/"+eventDto.getBride());
                    calenda.setStart(eventDto.getEventDate());
                    calenda.setId(eventDto.getEventId());
                    calenda.setBgColor("웨딩");

                    if(foodType.equals("뷔페")){
                        calenda.setColor("#e08282");
                        calenda.setTitle(calenda.getTitle()+"/"+foodCount+"-B");
                    }else if(foodType.equals("코스")){
                        calenda.setColor("#6f8bf2");
                        calenda.setTitle(calenda.getTitle()+"/"+foodCount+"-C");
                    }else if(foodType.equals("큐브")){
                        calenda.setColor("#189b04");
                        calenda.setTitle(calenda.getTitle()+"/"+foodCount+"-Q");
                    }else{
                        calenda.setColor("#000000");
                    }

                    calendarDtoList.add(calenda);
                }
            }
        }

        //기업행사 데이터 가공
        if(corpContList.size() > 0){
            String foodType = "";
            String foodCount = "";

            for(CorpContractDto corpCont : corpContList){
                CorpDto corpDto = corpService.findCorpsByCorpId(corpCont.getCorpId());

                List<CorpContractDto> imsiCorpContList = corpContractService.findCorpContractsByCorpId(corpCont.getCorpId());
                if(imsiCorpContList.size() > 0){
                    for(CorpContractDto imsiCorp : imsiCorpContList){
                        if(imsiCorp.getProdTitle().contains("뷔페")){
                            foodType = "뷔페";
                            foodCount = String.valueOf(imsiCorp.getCount());
                        }else if(imsiCorp.getProdTitle().contains("코스")){
                            foodType = "코스";
                            foodCount = String.valueOf(imsiCorp.getCount());
                        }else if(imsiCorp.getProdTitle().contains("큐브")){
                            foodType = "큐브";
                            foodCount = String.valueOf(imsiCorp.getCount());
                        }
                    }
                }

                if(corpDto != null){
                    CalendarDto calenda = new CalendarDto();

                    calenda.setTitle(corpDto.getEventTime()+"/"+corpDto.getCorp()+"/"+corpDto.getCorpTitle());
                    calenda.setStart(corpDto.getEventDate());
                    calenda.setId(corpDto.getCorpId());
                    calenda.setBgColor("기업");

                    if(foodType.equals("뷔페")){
                        calenda.setColor("#e08282");
                        calenda.setTitle(calenda.getTitle()+"/"+foodCount+"-B");
                    }else if(foodType.equals("코스")){
                        calenda.setColor("#6f8bf2");
                        calenda.setTitle(calenda.getTitle()+"/"+foodCount+"-C");
                    }else if(foodType.equals("큐브")){
                        calenda.setColor("#189b04");
                        calenda.setTitle(calenda.getTitle()+"/"+foodCount+"-Q");
                    }else{
                        calenda.setColor("#000000");
                    }

                    calendarDtoList.add(calenda);
                }
            }
        }

        return new ResponseEntity<List<CalendarDto>>(calendarDtoList, HttpStatus.OK);
    }

    //상담 메모 업데이트
    @RequestMapping("/updateDmemo")
    public ResponseEntity<String> updateDmemo(@RequestBody EventDto reqDto){
        EventDto currentDto = eventService.findEventByEventId(reqDto.getEventId());
        currentDto.setDirectingMemo(reqDto.getDirectingMemo());
        eventService.update(currentDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //기업행사 상담 메모 업데이트
    @RequestMapping("/updateCorpDmemo")
    public ResponseEntity<String> updateCorpDmemo(@RequestBody CorpDto reqDto){
        CorpDto currentDto = corpService.findCorpsByCorpId(reqDto.getCorpId());
        currentDto.setDirectingMemo(reqDto.getDirectingMemo());
        corpService.update(currentDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //플라워 메모 업데이트
    @RequestMapping("/updateFmemo")
    public ResponseEntity<String> updateFmemo(@RequestBody EventDto reqDto){
        EventDto currentDto = eventService.findEventByEventId(reqDto.getEventId());
        currentDto.setFlowerMemo(reqDto.getFlowerMemo());
        eventService.update(currentDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //기업행사 플라워 메모 업데이트
    @RequestMapping("/updateCorpFmemo")
    public ResponseEntity<String> updateCorpFmemo(@RequestBody CorpDto reqDto){
        CorpDto currentDto = corpService.findCorpsByCorpId(reqDto.getCorpId());
        currentDto.setFlowerMemo(reqDto.getFlowerMemo());
        corpService.update(currentDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //케이터링 메모 업데이트
    @RequestMapping("/updateCmemo")
    public ResponseEntity<String> updateCmemo(@RequestBody EventDto reqDto){
        EventDto currentDto = eventService.findEventByEventId(reqDto.getEventId());
        currentDto.setFoodMemo(reqDto.getFoodMemo());
        eventService.update(currentDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //기업행사 케이터링 메모 업데이트
    @RequestMapping("/updateCorpCmemo")
    public ResponseEntity<String> updateCorpCmemo(@RequestBody CorpDto reqDto){
        CorpDto currentDto = corpService.findCorpsByCorpId(reqDto.getCorpId());
        currentDto.setFoodMemo(reqDto.getFoodMemo());
        corpService.update(currentDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //기업행사 계약항목 취소처리
    @RequestMapping("/cancelCorpContList")
    public ResponseEntity<String> cancelCorpContList(@RequestBody List<CorpContractDto> reqList){
        if(reqList.size() > 0){
            for(CorpContractDto imsi : reqList){
                CorpContractDto currentDto = corpContractService.findCorpContractByContId(imsi.getContId());
                currentDto.setCancel(LocalDate.now().toString());
                corpContractService.update(currentDto);
            }
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    //계약항목 취소처리
    @RequestMapping("/cancelContList")
    public ResponseEntity<String> cancelContList(@RequestBody List<ContractDto> reqList){
        if(reqList.size() > 0){
            for(ContractDto imsi : reqList){
                ContractDto currentDto = contractService.findContractByContId(imsi.getContId());
                currentDto.setCancel(LocalDate.now().toString());
                contractService.update(currentDto);
            }
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    //event 정보 업데이트-디렉팅
    @RequestMapping("/updateDirectingEvent")
    public ResponseEntity<String> updateDirectingEvent(@RequestBody EventDto reqDto){
        //취소, 생성일은 기존 정보 살리기
        EventDto currentDto = eventService.findEventByEventId(reqDto.getEventId());
        if (currentDto != null) {
            reqDto.setCancel(currentDto.getCancel());
            reqDto.setCreateDate(currentDto.getCreateDate());
        }
        eventService.update(reqDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //event 정보 업데이트-계약관리
    @RequestMapping("/updateEvent")
    public ResponseEntity<String> updateEvent(@RequestBody EventDto reqDto) {
        //메모, 취소, 생성일은 기존 정보 살리기
        EventDto currentDto = eventService.findEventByEventId(reqDto.getEventId());
        if (currentDto != null) {
            reqDto.setDirectingMemo(currentDto.getDirectingMemo());
            reqDto.setFlowerMemo(currentDto.getFlowerMemo());
            reqDto.setFoodMemo(currentDto.getFoodMemo());
            reqDto.setCancel(currentDto.getCancel());
            reqDto.setCreateDate(currentDto.getCreateDate());
        }


        eventService.update(reqDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //corp 정보 업데이트-계약관리
    @RequestMapping("/updateCorp")
    public ResponseEntity<String> updateCorp(@RequestBody CorpDto reqDto) {
        //메모, 취소, 생성일은 기존 정보 살리기
        CorpDto currentDto = corpService.findCorpsByCorpId(reqDto.getCorpId());
        if (currentDto != null) {
            reqDto.setDirectingMemo(currentDto.getDirectingMemo());
            reqDto.setFlowerMemo(currentDto.getFlowerMemo());
            reqDto.setFoodMemo(currentDto.getFoodMemo());
            reqDto.setCancel(currentDto.getCancel());
            reqDto.setCreateDate(currentDto.getCreateDate());
        }


        corpService.update(reqDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //corp 정보 업데이트-디렉팅
    @RequestMapping("/updateCorpDirecting")
    public ResponseEntity<String> updateCorpDirecting(@RequestBody CorpDto reqDto) {
        //메모, 취소, 생성일은 기존 정보 살리기
        CorpDto currentDto = corpService.findCorpsByCorpId(reqDto.getCorpId());
        if (currentDto != null) {
            reqDto.setCancel(currentDto.getCancel());
            reqDto.setCreateDate(currentDto.getCreateDate());
        }


        corpService.update(reqDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    //기업행사 계약항목 불러오기
    @RequestMapping("/findCorpContListByCorpId")
    public ResponseEntity<List<CorpContractDto>> findCorpContListByCorpId(@RequestBody CorpContractDto reqDto) {
        List<CorpContractDto> currentList = corpContractService.findCorpContractsByCorpId(reqDto.getCorpId());

        return new ResponseEntity<List<CorpContractDto>>(currentList, HttpStatus.OK);
    }

    //계약항목 불러오기
    @RequestMapping("/findContListByEventId")
    public ResponseEntity<List<ContractDto>> findContListByEventId(@RequestBody ContractDto reqDto) {

        List<ContractDto> currentList = contractService.findContractsByEventId(reqDto.getEventId());

        if(currentList.size() > 0){
            for(ContractDto cont : currentList){
                cont.setState("등록됨");
            }
        }

        return new ResponseEntity<List<ContractDto>>(currentList, HttpStatus.OK);
    }

    //계약항목 업데이트
    @RequestMapping("/updateContList")
    public ResponseEntity<String> updateContList(@RequestBody List<ContractDto> reqList) {

        if (reqList.size() > 0) {

            for (ContractDto imsi : reqList) {
                ContractDto currentDto = contractService.findContractByContId(imsi.getContId());
                if (currentDto == null) {
                    //신규 계약항목
                    imsi.setCreateDate(LocalDate.now().toString());
                    contractService.save(imsi);
                } else {
                    //업데이트
                    //취소항목은 취소 날짜 이전
                    imsi.setCancel(currentDto.getCancel());
                    //생성 날짜 이전
                    imsi.setCreateDate(currentDto.getCreateDate());
                    contractService.update(imsi);
                }
            }
            return new ResponseEntity<String>(String.valueOf(reqList.get(0).getEventId()), HttpStatus.OK);
        } else {
            //업데이트 된 항목 없음
            return new ResponseEntity<String>("noUpdate", HttpStatus.OK);
        }
    }

    //기업행사 계약항목 업데이트
    @RequestMapping("/updateCorpContList")
    public ResponseEntity<String> updateCorpContList(@RequestBody List<CorpContractDto> reqList) {

        if (reqList.size() > 0) {

            for (CorpContractDto imsi : reqList) {
                CorpContractDto currentDto = corpContractService.findCorpContractByContId(imsi.getContId());
                if (currentDto == null) {
                    //신규 계약항목
                    imsi.setCreateDate(LocalDate.now().toString());
                    corpContractService.save(imsi);
                } else {
                    //업데이트
                    //취소항목은 취소 날짜 이전
                    imsi.setCancel(currentDto.getCancel());
                    //생성 날짜 이전
                    imsi.setCreateDate(currentDto.getCreateDate());
                    corpContractService.update(imsi);
                }
            }
            return new ResponseEntity<String>(String.valueOf(reqList.get(0).getCorpId()), HttpStatus.OK);
        } else {
            //업데이트 된 항목 없음
            return new ResponseEntity<String>("noUpdate", HttpStatus.OK);
        }
    }

    //event 이메일 검색
    @RequestMapping("/searchEventByEmail")
    public ResponseEntity<List<EventDto>> searchEventByEmail(@RequestBody EventDto reqDto) {
        List<EventDto> result = eventService.findEventsByEmail(reqDto.getEmail());

        return new ResponseEntity<List<EventDto>>(result, HttpStatus.OK);
    }

    //event 전화번호 검색
    @RequestMapping("/searchEventByHp")
    public ResponseEntity<List<EventDto>> searchEventByHp(@RequestBody EventDto reqDto) {
        List<EventDto> result = eventService.findEventsByHp(reqDto.getGroomHp());

        return new ResponseEntity<List<EventDto>>(result, HttpStatus.OK);
    }

    //Event 이름 검색
    @RequestMapping("/searchEventByName")
    public ResponseEntity<List<EventDto>> searchEventByName(@RequestBody EventDto reqDto) {
        List<EventDto> result = eventService.findEventsByName(reqDto.getGroom());

        return new ResponseEntity<List<EventDto>>(result, HttpStatus.OK);
    }

    //eventId로 이벤트 정보가져오기
    @RequestMapping("/findEventByEventId")
    public ResponseEntity<EventDto> findEventByEventId(@RequestBody EventDto reqDto) {
        return new ResponseEntity<EventDto>(eventService.findEventByEventId(reqDto.getEventId()), HttpStatus.OK);
    }

    //신규 이벤트 등록
    @RequestMapping("/addNewEvent")
    public ResponseEntity<String> addNewEvent(@RequestBody EventDto reqDto) {
        //기존에 동일한 정보가 있는지 체크
        List<EventDto> currentList = new ArrayList<EventDto>();

        if(!reqDto.getGroomHp().equals("010")){
            currentList = eventService.findEventsByTel(reqDto.getGroomHp());

            if (currentList.size() > 0) {
                return new ResponseEntity<String>("groomHp", HttpStatus.OK);
            }
        }

        if(!reqDto.getBrideHp().equals("010")){
            currentList = eventService.findEventsByTel(reqDto.getBrideHp());
            if (currentList.size() > 0) {
                return new ResponseEntity<String>("brideHp", HttpStatus.OK);
            }
        }

        //등록일자 설정
        String toDay = LocalDate.now().toString();
        reqDto.setCreateDate(toDay);
        Long savedId = eventService.save(reqDto);

        //초기 계약항목(대관&연출) 등록
        if(reqDto.getContList().size() > 0){
            for(ContractDto cont : reqDto.getContList()){
                cont.setEventId(savedId);
                contractService.save(cont);
            }
        }

        return new ResponseEntity<String>(String.valueOf(savedId), HttpStatus.OK);
    }

    //가입 id 찾기
    @RequestMapping("/findSignupEmail")
    public ResponseEntity<String> findSignupEmail(@RequestBody UserDto reqDto) {
        UserDto currentDto = userService.findUserByHp(reqDto.getHp());

        if (currentDto == null) {
            return new ResponseEntity<String>("noData", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(currentDto.getEmail(), HttpStatus.OK);
        }
    }

    //가입 pw 찾기
    @RequestMapping("/findSignupPass")
    public ResponseEntity<String> findSignupPass(@RequestBody UserDto reqDto) {
        UserDto currentDto = userService.findUserByHp(reqDto.getHp());

        if (currentDto == null) {
            return new ResponseEntity<String>("noData", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(currentDto.getPass(), HttpStatus.OK);
        }
    }

    @RequestMapping("/signupEmail")
    public ResponseEntity<String> signupEmail(@RequestBody UserDto reqDto) {

        //중복된 이메일이 있는지 체크
        UserDto currentDto = userService.findUserByEmail(reqDto.getEmail());
        if (currentDto == null) {
            userService.save(reqDto);
            return new ResponseEntity<String>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("duplicated", HttpStatus.OK);
        }
    }
}
