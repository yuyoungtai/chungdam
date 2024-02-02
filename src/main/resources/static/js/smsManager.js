const weddingHeader = `<div class="fw-bold d-flex flex-wrap align-items-center text-center">
        <div class="col-1"><input id="wedding-all-check-btn" type="checkbox"></div>
        <div class="col">행사일</div>
        <div class="col">시간</div>
        <div class="col">신랑</div>
        <div class="col">신부</div>
    </div>`;

const corpHeader = `<div class="fw-bold d-flex flex-wrap align-items-center text-center">
        <div class="col-1"><input id="all-check-btn" type="checkbox"></div>
        <div class="col">행사일</div>
        <div class="col">행사시간</div>
        <div class="col">회사명</div>
        <div class="col">행사명</div>
    </div>`;

const allBtnListener = () => {
    //웨딩 이벤트 전체 체크
    document.querySelector('#wedding-all-check-btn').addEventListener('change', ()=>{
        if(document.querySelector('#wedding-all-check-btn').checked){
            //체크
            document.querySelectorAll('.wedding-check').forEach(item => {
                item.checked=true;
            });
        }else {
            document.querySelectorAll('.wedding-check').forEach(item => {
                item.checked = false;
            });
        }
    });
}

const corpAllBtnListener = () => {
    //기업 이벤트 전체 체크
    document.querySelector('#all-check-btn').addEventListener('change', ()=>{
        if(document.querySelector('#all-check-btn').checked){
            //체크
            document.querySelectorAll('.corp-check').forEach(item => {
                item.checked=true;
            });
        }else {
            document.querySelectorAll('.corp-check').forEach(item => {
                item.checked = false;
            });
        }
    });
}

//수신 대상 검색
const searchCustomer = () => {
    if(document.querySelector('#corp-filter-check').checked) {
        //기업행사 검색
        searchCorpEvent();
    }else{
        //웨딩행사 검색
        searchWeddingEvent();
    }
}

const searchWeddingEvent = async () => {
    const startDate = document.querySelector('#start-date').value;
    const endDate = document.querySelector('#end-date').value;

    if(startDate === ''){
        alert('시작일자를 입력하세요.');
    }else if(endDate === ''){
        alert('종료일자를 입력하세요.');
    }else{
        try{
            await axios.post('/findWeddingByPeriod', {
                startDate, endDate
            }).then(response => {
                if(response.data.length === 0){
                    document.querySelector('#result-header').innerHTML = `<p class="text-center fw-bold">검색 결과가 없습니다.</p>`;
                }else{
                    document.querySelector('#result-header').innerHTML = weddingHeader;

                    let tag = '';
                    response.data.forEach(item => {
                        tag += `
                        <div class="d-flex flex-wrap align-items-center text-center mb-3">
                            <div class="col-1"><input class="wedding-check receiver" type="checkbox" eventid="${item.eventId}" checked></div>
                            <div class="col">${item.eventDate}</div>
                            <div class="col">${item.eventTime}</div>
                            <div class="col">${item.groom}</div>
                            <div class="col">${item.bride}</div>
                        </div>`;
                    });
                    document.querySelector('#result-wrap').innerHTML = tag;
                    allBtnListener();
                }
            });
        }catch (e) {
            alert('웨딩행사 검색 오류: '+ e);
        }
    }
}

const searchCorpEvent = async () => {
    const startDate = document.querySelector('#start-date').value;
    const endDate = document.querySelector('#end-date').value;

    if(startDate === ''){
        alert('시작일자를 입력하세요.');
    }else if(endDate === ''){
        alert('종료일자를 입력하세요.');
    }else{
        try{
            await axios.post('/findCorpByPeriod', {
                startDate, endDate
            }).then(response => {
                if(response.data.length === 0){
                    document.querySelector('#result-header').innerHTML = `<p class="text-center fw-bold">검색 결과가 없습니다.</p>`;
                }else{
                    document.querySelector('#result-header').innerHTML = corpHeader;

                    let tag = '';
                    response.data.forEach(item => {
                        tag += `
                        <div class="d-flex flex-wrap align-items-center text-center mb-3">
                            <div class="col-1"><input class="corp-check receiver" type="checkbox" eventid="${item.corpId}" checked></div>
                            <div class="col">${item.eventDate}</div>
                            <div class="col">${item.eventTime}</div>
                            <div class="col">${item.corp}</div>
                            <div class="col">${item.corpTitle}</div>
                        </div>`;
                    });
                    document.querySelector('#result-wrap').innerHTML = tag;
                    corpAllBtnListener();
                }
            });
        }catch (e) {
            alert('웨딩행사 검색 오류: '+ e);
        }
    }
}

const sendDM = async () => {
    const checkLists = document.querySelectorAll('.receiver:checked');
    if(checkLists.length === 0){
        alert('수신자를 선택하지 않았습니다.');
        return false;
    }else{
        //수신자 데이터 생성
        let receiverList = new Array();

        console.log(document.querySelector('#corp-filter-check').checked);
        if(document.querySelector('#corp-filter-check').checked){
            //기업행사
            const msg = document.querySelector('#msg-input').value;
            for(const row of checkLists) {
                const data = {
                    id: row.attributes.eventid.value,
                    type: 'corp',
                    msg: msg
                }
                receiverList.push(data);
            }
        }else{
            //웨딩 행사
            const msg = document.querySelector('#msg-input').value;
            for(const row of checkLists) {
                const data = {
                    id: row.attributes.eventid.value,
                    type: 'wedding',
                    msg: msg
                }
                receiverList.push(data);
            }
        }

        //메시지 발송
        try{
            await axios.post('/sendDM', receiverList).then(response =>{
                alert('메시지 발송 완료');
                self.location.reload();
            });
        }catch (e) {
            alert('메시지 발송 오류: '+ e);
        }
    }
}
