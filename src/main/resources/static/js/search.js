//고객 검색 버튼
const searchEvent = () => {
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
            email: document.querySelector('#search-email-input').value
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
            //addContract.js
            printEventInfo(response.data);
            //계약정보 출력
            //search.js
            getContractList(response.data.eventId);
            const currentModal = bootstrap.Modal.getInstance(document.querySelector('#search-result-modal'));
            currentModal.hide();
        });
    }catch (e) {
        alert('이벤트 정보 로딩 오류: '+e);
    }
}

//계약항목 불러오기
const getContractList = async (eventId) => {
    try{
        await axios.post('/findContListByEventId', {
            eventId
        }).then(response=>{
            //search.js
           printContList(response.data);
        });
    }catch (e) {
        alert('계약항목 불러오기 오류: '+e);
    }
}


//계약항목 출력
const printContList = (contList) => {
    if(contList.length > 0){
        document.querySelector('#contract-btn-group').classList.remove('invisible');

        let tag = '';
        let contTotal = 0;

        contList.forEach(item => {

            if(item.cancel !== null){
                //취소건
                tag += `
            <div class="d-flex flex-nowrap text-center align-items-center">
                <div class="col-1"><input type="checkbox" class="cont-check" no="${item.contId}" checked></div>
                <div class="col-2 col-md-1">${item.state}</div>
                <div class="col-4 col-md-4 fw-bold"><input type="text" class="form-control text-center cont-title text-danger" value="${item.prodTitle}"></div>
                <div class="col-2 col-md-3 fw-bold"><input type="text" class="form-control text-center cont-supply text-danger" value="`+numberWithCommas(item.applyPrice)+`" onkeyup="getRowTotal(this);"></div>
                <div class="col-1 fw-bold"><input type="text" class="form-control text-center cont-count text-danger" value="`+numberWithCommas(item.count)+`" onkeyup="getRowTotal(this);"></div>
                <div class="col-2 fw-bold"><input type="text" class="form-control text-center cont-total text-danger" value="`+numberWithCommas(item.total)+`" readonly></div>
            </div>`;
            }else{
                //정상계약건
                contTotal += item.total;
                tag += `
            <div class="d-flex flex-nowrap text-center align-items-center">
                <div class="col-1"><input type="checkbox" class="cont-check" no="${item.contId}" checked></div>
                <div class="col-2 col-md-1">${item.state}</div>
                <div class="col-4 col-md-4 fw-bold"><input type="text" class="form-control text-center cont-title" value="${item.prodTitle}"></div>
                <div class="col-2 col-md-3 fw-bold"><input type="text" class="form-control text-center cont-supply" value="`+numberWithCommas(item.applyPrice)+`" onkeyup="getRowTotal(this);"></div>
                <div class="col-1 fw-bold"><input type="text" class="form-control text-center cont-count" value="`+numberWithCommas(item.count)+`" onkeyup="getRowTotal(this);"></div>
                <div class="col-2 fw-bold"><input type="text" class="form-control text-center cont-total" value="`+numberWithCommas(item.total)+`" readonly></div>
            </div>`;
            }
        });
        document.querySelector('#cont-wrap').innerHTML = tag;
        document.querySelector('#total-price-wrap').innerHTML = `<hr><div class="justify-content-end d-flex my-5 text-end"><div class="col-1 fw-bold">전체총액</div><div class="col-2 fw-bold">`
            +numberWithCommas(contTotal)+`</div></div>`;
    }
}