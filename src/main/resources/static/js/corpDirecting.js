//기업행사 데이터 모달로 띄우기
const selCorpData = async (corpId) => {
    try{
        await axios.post('/findCorpByCorpId', {
            corpId
        }).then(response => {
           console.log(response.data);
           const corpModal = new bootstrap.Modal(document.querySelector('#corp-info-modal'));
            corpModal.show();

            //기업행사 정보 출력
           printCorpInfo(response.data);
           //계약 항목 출력. directingCorpContList();
            getCorpContractList(response.data.corpId);
        });
    }catch (e) {
        alert('기업행사 정보 로딩 오류: '+e);
    }
}

//기업행사 정보 출력
const printCorpInfo = (data) => {
    document.querySelector('#corp').value = data.corp;
    document.querySelector('#corp-title').value = data.corpTitle;
    document.querySelector('#corp-event-date').value = data.eventDate;
    document.querySelector('#corp-event-time').value = data.eventTime;
    document.querySelector('#corp-person').value = data.person;
    document.querySelector('#guest').value = data.guest;
    document.querySelector('#guest-hp').value = data.guestHp;
    document.querySelector('#corp-email').value = data.email;
    document.querySelector('#sel-corp-id').value = data.corpId;
    document.querySelector('#corp-d-memo-info').value = data.directingMemo;
    document.querySelector('#corp-f-memo-info').value = data.flowerMemo;
    document.querySelector('#corp-c-memo-info').value = data.foodMemo;
}

//기업행사 내용 저장
const saveCorpInfo = async () => {
    const corp = document.querySelector('#corp').value;
    const corpTitle = document.querySelector('#corp-title').value;
    const eventDate = document.querySelector('#corp-event-date').value;
    const eventTime = document.querySelector('#corp-event-time').value;
    const person = document.querySelector('#corp-person').value;
    const guest = document.querySelector('#guest').value;
    const email = document.querySelector('#corp-email').value;
    const guestHp = document.querySelector('#guest-hp').value;
    const corpId = document.querySelector('#sel-corp-id').value;
    const directingMemo = document.querySelector('#corp-d-memo-info').value;
    const flowerMemo = document.querySelector('#corp-f-memo-info').value;
    const foodMemo = document.querySelector('#corp-c-memo-info').value;

    try{
        await axios.post('/updateCorpDirecting',{
            corpId, corp, corpTitle, eventDate, eventTime, person, guest, guestHp, directingMemo, flowerMemo, foodMemo, email
        }).then(response => {
            alert('저장 완료!');
            self.location.reload();
        });
    }catch (e) {
        alert('기업행사 정보 저장 오류:'+e);
    }
}