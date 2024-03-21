//신규 계약 등록 버튼
const showNewCorpModal = () => {
    const addModal = new bootstrap.Modal(document.querySelector('#add-corp-modal'))
    addModal.show();
    initCorpModal();
}

//addModal 초기화
const initCorpModal = () => {
    document.querySelector('#add-corp').value = '';
    document.querySelector('#add-corp-title').value = '';
    document.querySelector('#add-guest').value = '';
    document.querySelector('#add-guest-hp').value = '';
    document.querySelector('#add-corp-event-date').value = '';
    document.querySelector('#add-corp-event-time').value = '';
    document.querySelector('#add-corp-person').value = '';
    document.querySelector('#add-corp-email').value = '';
}

//신규 기업행 등록
const addNewCorp = async () => {
    if (checkAddCorpData()) {
        try {
            const corp = document.querySelector('#add-corp').value;
            const corpTitle = document.querySelector('#add-corp-title').value;
            const guest = document.querySelector('#add-guest').value;
            const guestHp = document.querySelector('#add-guest-hp').value;
            const email = document.querySelector('#add-corp-email').value;
            const eventDate = document.querySelector('#add-corp-event-date').value;
            const eventTime = document.querySelector('#add-corp-event-time').value;
            const person = document.querySelector('#add-corp-person').value;

            await axios.post('/addNewCorpEvent', {
                corp, corpTitle, guest, guestHp, email, eventDate, eventTime, person
            }).then(response => {

                alert('등록이 완료되었습니다.');
                const addModal = bootstrap.Modal.getInstance(document.querySelector('#add-corp-modal'));
                addModal.hide();

                //저장된 eventId 반환
                findCorpByCorpId(response.data);

            });
        } catch (e) {
            alert('신규 이벤트 등록 오류: ' + e);
        }
    }
}

//이벤트 등록 데이터 체크
function checkAddCorpData() {
    const corp = document.querySelector('#add-corp').value;
    const corpTitle = document.querySelector('#add-corp-title').value;
    const guest = document.querySelector('#add-guest').value;
    const guestHp = document.querySelector('#add-guest-hp').value;
    //const email = document.querySelector('#add-corp-email').value;
    const eventDate = document.querySelector('#add-corp-event-date').value;
    const eventTime = document.querySelector('#add-corp-event-time').value;
    const person = document.querySelector('#add-corp-person').value;

    if (corp === '') {
        alert('기업명을 입력하세요.');
        return false;
    } else if (corpTitle === '') {
        alert('행사명을 입력하세요.');
        return false;
    } else if (guest === '') {
        alert('담당자 성함을 입력하세요.');
        return false;
    } else if (guestHp === '') {
        alert('담당자 연락처를 입력하세요.');
        return false;
    } else if (eventDate === '') {
        alert('행사일을 입력하세요.');
        return false;
    } else if (eventTime === '') {
        alert('행시간을 입력하세요.');
        return false;
    } else if (person === '') {
        alert('보증인원을 입력하세요.');
        return false;
    } else {
        return true;
    }
}


//corpId로 이벤트 정보 가져오기
const findCorpByCorpId = async (corpId) => {
    try {
        await axios.post('/findCorpByCorpId', {
            corpId
        }).then(response => {
            //이벤트 정보 출력
            printCorpInfo(response.data);
        });
    } catch (e) {
        alert('이벤트 정보 로딩 오류: ' + e);
    }
}

//event 정보 출력
const printCorpInfo = (event) => {
    document.querySelector('#event-date').value = event.eventDate;
    document.querySelector('#event-time').value = event.eventTime;
    document.querySelector('#person').value = event.person;
    document.querySelector('#email').value = event.email;
    document.querySelector('#corp').value = event.corp;
    document.querySelector('#corp-title').value = event.corpTitle;
    document.querySelector('#guest').value = event.guest;
    document.querySelector('#guest-hp').value = event.guestHp;
    document.querySelector('#current-corp-id').value = event.corpId;
    document.querySelector('#d-memo-info').value = event.directingMemo;
    document.querySelector('#f-memo-info').value = event.flowerMemo;
    document.querySelector('#c-memo-info').value = event.foodMemo;

    //사업자 등록증
    if (event.tex === null) {
        document.querySelector('#tex-switch').checked = false;
        document.querySelector('#tex-down-btn1').removeAttribute('src');
        document.querySelector('#tex-down-btn2').removeAttribute('src');
    } else {
        document.querySelector('#tex-switch').checked = true;
        document.querySelector('#tex-down-btn1').setAttribute('src', event.tex);
        document.querySelector('#tex-down-btn2').setAttribute('src', event.tex);
    }

    //계약 버튼 그룹 토글
    document.querySelector('#contract-btn-group').classList.remove('invisible');
}