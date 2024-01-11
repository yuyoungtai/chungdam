//상담메모 토글
const showDmemo = () =>{
    document.querySelector('#d-memo-wrap').classList.toggle('d-none');
}
//플라워 메모 토글
const showFmemo = () =>{
    document.querySelector('#f-memo-wrap').classList.toggle('d-none');
}
//케이터링 메모 토글
const showCmemo = () =>{
    document.querySelector('#c-memo-wrap').classList.toggle('d-none');
}

//상담메모 업데이트
const updateDmemo = async () => {
    try{
    await axios.post('/updateDmemo',{
        eventId: document.querySelector('#current-event-id').value,
        directingMemo: document.querySelector('#d-memo-info').value
    }).then(response=>{
       alert('저장 되었습니다.');
    });
    }catch (e) {
        alert('상담메모 저장오류: '+e);
    }
}


//플라워 메모 업데이트
const updateFmemo = async () => {
    try{
        await axios.post('/updateFmemo',{
            eventId: document.querySelector('#current-event-id').value,
            flowerMemo: document.querySelector('#f-memo-info').value
        }).then(response=>{
            alert('저장 되었습니다.');
        });
    }catch (e) {
        alert('플라워팀 메모 저장오류: '+e);
    }
}

//케이터링 메모 업데이트
const updateCmemo = async () => {
    try{
        await axios.post('/updateCmemo',{
            eventId: document.querySelector('#current-event-id').value,
            foodMemo: document.querySelector('#c-memo-info').value
        }).then(response=>{
            alert('저장 되었습니다.');
        });
    }catch (e) {
        alert('케이터링 메모 저장오류: '+e);
    }
}