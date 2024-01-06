//이벤트 등록 데이터 체크
function checkEventData(){
    const groom = document.querySelector('#groom').value;
    const groomHp = document.querySelector('#groom-hp').value;
    const bride = document.querySelector('#bride').value;
    const brideHp = document.querySelector('#bride-hp').value;
    const eventDate = document.querySelector('#event-date').value;
    const eventTime = document.querySelector('#event-time').value;
    const person = document.querySelector('#person').value;
    const email = document.querySelector('#email').value;

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
    }else if(email === ''){
        alert('Email을 입력하세요.');
        return false;
    }else{
        return true;
    }
}

const updateEvent = async () => {
    if(checkEventData()){
        const groom = document.querySelector('#groom').value;
        const groomHp = document.querySelector('#groom-hp').value;
        const bride = document.querySelector('#bride').value;
        const brideHp = document.querySelector('#bride-hp').value;
        const eventDate = document.querySelector('#event-date').value;
        const eventTime = document.querySelector('#event-time').value;
        const person = document.querySelector('#person').value;
        const email = document.querySelector('#email').value;
        try{
            await axios.post('/updateEvent',{
                eventId: document.querySelector('#current-event-id').value,
                groom, groomHp, bride, brideHp, eventDate, eventTime, person, email
            }).then(response=>{
                alert('수정 완료!');
            });
        }catch (e) {
            alert('고객정보 수정 오류: '+e);
        }
    }
}