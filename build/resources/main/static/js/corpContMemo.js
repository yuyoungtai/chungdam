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
const updateCorpDmemo = async () => {
    try{
        await axios.post('/updateCorpDmemo',{
            corpId: document.querySelector('#current-corp-id').value,
            directingMemo: document.querySelector('#d-memo-info').value
        }).then(response=>{
            alert('저장 되었습니다.');
        });
    }catch (e) {
        alert('상담메모 저장오류: '+e);
    }
}

//기업행사 플라워 메모 업데이트
const updateCorpFmemo = async () => {
    try{
        await axios.post('/updateCorpFmemo',{
            corpId: document.querySelector('#current-corp-id').value,
            flowerMemo: document.querySelector('#f-memo-info').value
        }).then(response=>{
            alert('저장 되었습니다.');
        });
    }catch (e) {
        alert('상담메모 저장오류: '+e);
    }
}

//케이터링 메모 업데이트
const updateCorpCmemo = async () => {
    try{
        await axios.post('/updateCorpCmemo',{
            corpId: document.querySelector('#current-corp-id').value,
            foodMemo: document.querySelector('#c-memo-info').value
        }).then(response=>{
            alert('저장 되었습니다.');
        });
    }catch (e) {
        alert('케이터링 메모 저장오류: '+e);
    }
}