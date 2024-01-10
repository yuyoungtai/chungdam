//이벤트 등록 데이터 체크
function checkEventData(){
    const corp = document.querySelector('#corp').value;
    const corpTitle = document.querySelector('#corp-title').value;
    const guest = document.querySelector('#guest').value;
    const guestHp = document.querySelector('#guest-hp').value;
    const email = document.querySelector('#email').value;
    const eventDate = document.querySelector('#event-date').value;
    const eventTime = document.querySelector('#event-time').value;
    const person = document.querySelector('#person').value;

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
    } else if (email === '') {
        alert('Email을 입력하세요.');
        return false;
    } else {
        return true;
    }
}

const updateCorp = async () => {
    if(checkEventData()){
        const corp = document.querySelector('#corp').value;
        const corpTitle = document.querySelector('#corp-title').value;
        const guest = document.querySelector('#guest').value;
        const guestHp = document.querySelector('#guest-hp').value;
        const email = document.querySelector('#email').value;
        const eventDate = document.querySelector('#event-date').value;
        const eventTime = document.querySelector('#event-time').value;
        const person = document.querySelector('#person').value;
        try{
            await axios.post('/updateCorp',{
                corpId: document.querySelector('#current-corp-id').value,
                corp, corpTitle, guest, guestHp, eventDate, eventTime, person, email
            }).then(response=>{
                alert('수정 완료!');
            });
        }catch (e) {
            alert('기업행사 정보 수정 오류: '+e);
        }
    }
}