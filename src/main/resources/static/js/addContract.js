//신규 계약 등록 버튼
const showNewContModal = () =>{
    const addModal = new bootstrap.Modal(document.querySelector('#add-cont-modal'))
    addModal.show();
    initAddModal();
}

//addModal 초기화
const initAddModal = () =>{
    document.querySelector('#add-groom').value = '';
    document.querySelector('#add-groom-hp').value = '';
    document.querySelector('#add-bride').value = '';
    document.querySelector('#add-bride-hp').value = '';
    document.querySelector('#add-event-date').value = '';
    document.querySelector('#add-event-time').value = '';
    document.querySelector('#add-person').value = '';
}

//신규 이벤트 등록
const addNewEvent = async () => {
    if(checkAddData()){
        try{
            const groom = document.querySelector('#add-groom').value;
            const groomHp = document.querySelector('#add-groom-hp').value;
            const bride = document.querySelector('#add-bride').value;
            const brideHp = document.querySelector('#add-bride-hp').value;
            const eventDate = document.querySelector('#add-event-date').value;
            const eventTime = document.querySelector('#add-event-time').value;
            const person = document.querySelector('#add-person').value;

            await axios.post('/addNewEvent', {
                groom, groomHp, bride, brideHp, eventDate, eventTime, person
            }).then(response => {
                if(response.data === 'groomHp'){
                    alert('기존에 신랑님 연락처로 등록된 정보가 존재합니다.');
                }else if(response.data === 'brideHp'){
                    alert('기존에 신부님 연락처로 등록된 정보가 존재합니다.');
                }else{
                    //저장된 eventId 반환
                    findEventByEventId(response.data);
                }
            });
        }catch (e) {
            alert('신규 이벤트 등록 오류: '+e);
        }
    }
}

//이벤트 등록 데이터 체크
function checkAddData(){
    const groom = document.querySelector('#add-groom').value;
    const groomHp = document.querySelector('#add-groom-hp').value;
    const bride = document.querySelector('#add-bride').value;
    const brideHp = document.querySelector('#add-bride-hp').value;
    const eventDate = document.querySelector('#add-event-date').value;
    const eventTime = document.querySelector('#add-event-time').value;
    const person = document.querySelector('#add-person').value;

    if(groom === ''){
        alert('신랑님 성함을 입력하세요.');
        return false;
    }else if(groomHp === ''){
        alert('신랑님 연락처를 입력하세요.');
        return false;
    }else if(bride === ''){
        alert('신부님 성함을 입력하세요.');
        return false;
    }else if(brideHp === ''){
        alert('신부님 연락처를 입력하세요.');
        return false;
    }else if(eventDate === ''){
        alert('예식일을 입력하세요.');
        return false;
    }else if(eventTime === ''){
        alert('예식시간을 입력하세요.');
        return false;
    }else if(person === ''){
        alert('보증인원을 입력하세요.');
        return false;
    }else{
        return true;
    }
}

//eventId로 이벤트 정보 가져오기
const findEventByEventId = async (eventId) =>{

}