//계약 항목 행 추가
const addRow = ()=> {
    const rowTag = `
        <div class="d-flex flex-nowrap text-center align-items-center">
            <div class="col-1"><input type="checkbox" class="cont-check" no="0"></div>
            <div class="col-4 col-md-4 fw-bold"><input type="text" class="form-control text-center cont-title"></div>
            <div class="col-3 fw-bold"><input type="text" class="form-control text-center cont-supply" value="0" onkeyup="getRowTotal(this);"></div>
            <div class="col-1 fw-bold"><input type="text" class="form-control text-center cont-count" value="1" onkeyup="getRowTotal(this);"></div>
            <div class="col-3 fw-bold"><input type="text" class="form-control text-center cont-total" value="0" readonly></div>
        </div>
    `;
    document.querySelector('#cont-wrap').insertAdjacentHTML('beforeend', rowTag);
}

//추가한 행 항목 계산
const getRowTotal = (e) =>{
    let supplyPrice = getNumber(e.parentElement.parentElement.querySelector('.cont-supply').value)/10*10;
    let count = getNumber(e.parentElement.parentElement.querySelector('.cont-count').value)/10*10;

    let rowTotal = supplyPrice * count;
    const formatTotal = numberWithCommas(rowTotal);
    e.parentElement.parentElement.querySelector('.cont-total').value = formatTotal;

    checkRowtotal();

    //콤마표시
    e.parentElement.parentElement.querySelector('.cont-supply').value = numberWithCommas(supplyPrice);
    e.parentElement.parentElement.querySelector('.cont-count').value = numberWithCommas(count);
}

//체크된 항목 총액 계산
const checkRowtotal = () => {
    let totalPrice = 0;
    const checkList = document.querySelectorAll('.cont-check:checked');

    if(checkList.length > 0){
        checkList.forEach(item => {
            const supply = getNumber(item.parentElement.parentElement.querySelector('.cont-supply').value)/10*10;
            const count = getNumber(item.parentElement.parentElement.querySelector('.cont-count').value)/10*10;
            totalPrice += supply * count;
        });
        const formatTotal = numberWithCommas(totalPrice);
        document.querySelector('#total-price-wrap').innerHTML = `<hr><div class="justify-content-end d-flex my-5 text-end"><div class="col-1 fw-bold">전체총액</div><div class="col-2 fw-bold">${formatTotal}</div></div>`;
    }
}

//계약 항목 저장
const saveContList = async () => {
    //체크된 항목 가져오기
    const checkList = document.querySelectorAll('.cont-check:checked');

    if(checkList.length > 0){
        let DATA_LIST = new Array();
        checkList.forEach(item => {
            const data = {
                contId : item.attributes.no.value,
                eventId: document.querySelector('#current-event-id').value,
                prodTitle: item.parentElement.parentElement.querySelector('.cont-title').value,
                applyPrice: getNumber(item.parentElement.parentElement.querySelector('.cont-supply').value),
                count: getNumber(item.parentElement.parentElement.querySelector('.cont-count').value),
                total: getNumber(item.parentElement.parentElement.querySelector('.cont-total').value)
            }
            DATA_LIST.push(data);
        })
        try{
            await axios.post('/updateContList', DATA_LIST)
                .then(response => {
                    alert('계약항목 저장 완료!');
                    if(response.data === 'noUpdate'){
                        //업데이트 된 내용 없음
                    }else{
                        //eventId 리턴 -> 계약항목 불러오기
                        //search.js
                        getContractList(response.data);
                    }
                });
        }catch (e) {
            alert('계약항목 저장오류: '+e);
        }
    }
}

//모든 항목 체크 on/off
document.querySelector('#all-check-btn').addEventListener('click', (e) =>
    {
        const checkedList = document.querySelectorAll('input.cont-check');
        if (e.target.checked) {
            for (const imsi of checkedList) {
                imsi.checked = true;
            }
        } else {
            for (const imsi of checkedList) {
                imsi.checked = false;
            }
        }
    }
);

//선택된 계약항목 취소
const cancelContList = async () => {
    const cancelList = document.querySelectorAll('.cont-check:checked');
    if(cancelList.length > 0){
        let DATA_LIST = new Array();
        cancelList.forEach(item => {
            const data = {
                contId: item.attributes.no.value
            }
            DATA_LIST.push(data);
        })
        try{
            await axios.post('/cancelContList', DATA_LIST)
                .then(response=>{
                    alert('취소 처리 완료!');
                    //search.js
                    getContractList(document.querySelector('#current-event-id').value);
                })
        }catch (e) {
            alert('계약항목 삭제 오류:'+e);
        }
    }
}