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
            const currentModal = bootstrap.Modal.getInstance(document.querySelector('#search-result-modal'));
            currentModal.hide();
        });
    }catch (e) {
        alert('이벤트 정보 로딩 오류: '+e);
    }
}
