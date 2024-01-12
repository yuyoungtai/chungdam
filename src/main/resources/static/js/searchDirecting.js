//이벤트 정보 초기화
const initDirectingView = () => {
    document.querySelector('#email').value = '';
    document.querySelector('#groom').value = '';
    document.querySelector('#groom-hp').value = '';
    document.querySelector('#bride').value = '';
    document.querySelector('#bride-hp').value = '';
    document.querySelector('#event-date').value = '';
    document.querySelector('#event-time').value = '';
    document.querySelector('#person').value = '';

    //디렉팅 파일 섹션 토글
    document.querySelector('#directing-section').classList.add('d-none');

    //메모 섹션 토글
    document.querySelector('#memo-section').classList.add('d-none');
}

const searchDirecting = () => {
    if (document.querySelector('#search-name-input').value !== '') {
        //이름검색
        searchEventByName(document.querySelector('#search-name-input').value);
    } else if (document.querySelector('#search-hp-input').value !== '') {
        //휴대폰 검색
        searchEventByHp(document.querySelector('#search-hp-input').value);
    } else if (document.querySelector('#search-email-input').value !== '') {
        //이메일 검색
        searchEventByEmail(document.querySelector('#search-email-input').value);
    } else {
        //검색란 미입력
        alert(`검색하려는 '전화번호 or 이름 or 이메일' 중 1개를 입력하세요.`);
    }

    document.querySelector('#search-name-input').value = '';
    document.querySelector('#search-hp-input').value = '';
    document.querySelector('#search-email-input').value = '';
}

//이름 검색
const searchEventByName = async () => {
    try{
        await axios.post('/searchEventByName', {
            groom: document.querySelector('#search-name-input').value
        }).then(response => {
            if(response.data.length === 0){
                alert('결과가 없습니다.');
            }else{
                const resultModal = new bootstrap.Modal(document.querySelector('#search-result-modal'));
                resultModal.show();

                //검색 결과 출력
                let tag = '';
                response.data.forEach(item => {
                    tag += `
                        <div class="d-flex text-center my-2">
                            <div class="col">${item.eventDate}</div>
                            <div class="col">${item.eventTime}</div>
                            <div class="col">${item.groom}</div>
                            <div class="col">${item.groomHp}</div>
                            <div class="col">${item.bride}</div>
                            <div class="col">${item.brideHp}</div>
                            <div class="col"><button class="btn btn-success" onclick="getEventByEventId('${item.eventId}');">선택</button></div>
                        </div>`;
                });
                document.querySelector('#search-result-wrap').innerHTML = tag;
            }
        });
    }catch (e) {
        alert('이름검색 오류: '+e);
    }
}
//전화번호 검색
const searchEventByHp = async () => {
    try{
        await axios.post('/searchEventByHp', {
            groomHp: document.querySelector('#search-hp-input').value
        }).then(response => {
            if(response.data.length === 0){
                alert('결과가 없습니다.');
            }else{
                const resultModal = new bootstrap.Modal(document.querySelector('#search-result-modal'));
                resultModal.show();

                //검색 결과 출력
                let tag = '';
                response.data.forEach(item => {
                    tag += `
                        <div class="d-flex text-center my-2">
                            <div class="col">${item.eventDate}</div>
                            <div class="col">${item.eventTime}</div>
                            <div class="col">${item.groom}</div>
                            <div class="col">${item.groomHp}</div>
                            <div class="col">${item.bride}</div>
                            <div class="col">${item.brideHp}</div>
                            <div class="col"><button class="btn btn-success" onclick="getEventByEventId('${item.eventId}');">선택</button></div>
                        </div>`;
                });
                document.querySelector('#search-result-wrap').innerHTML = tag;
            }
        });
    }catch (e) {
        alert('전화번호 검색 오류: '+e);
    }
}

//이메일 검색
const searchEventByEmail = async () => {
    try{
        await axios.post('/searchEventByEmail', {
            groomHp: document.querySelector('#search-email-input').value
        }).then(response => {
            if(response.data.length === 0){
                alert('결과가 없습니다.');
            }else{
                const resultModal = new bootstrap.Modal(document.querySelector('#search-result-modal'));
                resultModal.show();

                //검색 결과 출력
                let tag = '';
                response.data.forEach(item => {
                    tag += `
                        <div class="d-flex text-center my-2">
                            <div class="col">${item.eventDate}</div>
                            <div class="col">${item.eventTime}</div>
                            <div class="col">${item.groom}</div>
                            <div class="col">${item.groomHp}</div>
                            <div class="col">${item.bride}</div>
                            <div class="col">${item.brideHp}</div>
                            <div class="col"><button class="btn btn-success" onclick="getEventByEventId('${item.eventId}');">선택</button></div>
                        </div>`;
                });
                document.querySelector('#search-result-wrap').innerHTML = tag;
            }
        });
    }catch (e) {
        alert('이메일 검색 오류: '+e);
    }
}

//eventId로 이벤트 정보 가져오기
const getEventByEventId = async (eventId) =>{
    try{
        await axios.post('/findEventByEventId', {
            eventId
        }).then(response => {
            //이벤트 정보 출력
            printEventInfo(response.data);
            //계약정보 출력
           // getContractList(response.data.eventId);
            const currentModal = bootstrap.Modal.getInstance(document.querySelector('#search-result-modal'));
            currentModal.hide();
        });
    }catch (e) {
        alert('이벤트 정보 로딩 오류: '+e);
    }
}

const printEventInfo = (eventData) => {
    document.querySelector('#sel-event-id').value = eventData.eventId;
    document.querySelector('#email').value = eventData.email;
    document.querySelector('#groom').value = eventData.groom;
    document.querySelector('#groom-hp').value = eventData.groomHp;
    document.querySelector('#bride').value = eventData.bride;
    document.querySelector('#bride-hp').value = eventData.brideHp;
    document.querySelector('#event-date').value = eventData.eventDate;
    document.querySelector('#event-time').value = eventData.eventTime;
    document.querySelector('#person').value = eventData.person;

    document.querySelector('#d-memo-info').value = eventData.directingMemo;
    document.querySelector('#f-memo-info').value = eventData.flowerMemo;
    document.querySelector('#c-memo-info').value = eventData.foodMemo;

    //디렉팅 파일 섹션 토글
    document.querySelector('#directing-section').classList.remove('d-none');

    //메모 섹션 토글
    document.querySelector('#memo-section').classList.remove('d-none');
}

//이벤트 데이터 가져오기
const getCalendarData = async () => {
    try{
        await axios.get('/getDirectingData')
            .then(response => {
               $('#calendar').fullCalendar('addEventSource', response.data);
            });
    }catch (e) {
        alert('데이터 가져오기 오류: '+e);
    }
}

//캘린더에서 선택한 이벤트 정보 가져오기
const selDirectingData = async (eventId) => {
    try{
        await axios.post('/findEventByEventId',{
            eventId
        }).then(response => {
            //이벤트 정보 출력
            printEventInfo(response.data);
            //디렉팅 파일 출력
            directingFileCheck(response.data.eventId);
            //계약 항목 가젹오기
            getContractList(response.data.eventId);
        });
    }catch (e) {
        alert('데이터 가져오기 오류: '+e);
    }
}

//이벤트 정보 저장
const saveEvent = async () => {
    try {
        await axios.post('/updateDirectingEvent', {
            eventId: document.querySelector('#sel-event-id').value,
            email    : document.querySelector('#email').value,
            groom      : document.querySelector('#groom').value,
            groomHp   : document.querySelector('#groom-hp').value,
            bride    : document.querySelector('#bride').value,
            brideHp : document.querySelector('#bride-hp').value,
            eventDate: document.querySelector('#event-date').value,
            eventTime: document.querySelector('#event-time').value,
            person   : document.querySelector('#person').value,
            directingMemo: document.querySelector('#d-memo-info').value,
            flowerMemo: document.querySelector('#f-memo-info').value,
            foodMemo: document.querySelector('#c-memo-info').value,
        }).then(response => {
            alert('수정완료!');
            self.location.reload();
        });
    } catch (e) {
        alert('이벤트 저장 오류: ' + e);
    }
}