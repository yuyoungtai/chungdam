//계약항목 불러오기
const getCorpContractList = async (corpId) => {
    try{
        await axios.post('/findCorpContListByCorpId', {
            corpId
        }).then(response=>{
            //search.js
            printCorpContList(response.data);
        });
    }catch (e) {
        alert('계약항목 불러오기 오류: '+e);
    }
}

//계약항목 출력
const printCorpContList = (contList) => {
    if(contList.length > 0){
        // document.querySelector('#contract-btn-group').classList.remove('invisible');

        let tag = '';
        let contTotal = 0;

        contList.forEach(item => {

            if(item.cancel !== null){
                //취소건
                tag += `
            <div class="d-flex flex-nowrap text-center align-items-center">              
                <div class="col-4 col-md-4 fw-bold"><input type="text" class="form-control text-center cont-title text-danger" value="${item.prodTitle}"></div>
                <div class="col-3 fw-bold"><input type="text" class="form-control text-center cont-supply text-danger" value="`+numberWithCommas(item.applyPrice)+`" onkeyup="getRowTotal(this);"></div>
                <div class="col-1 fw-bold"><input type="text" class="form-control text-center cont-count text-danger" value="`+numberWithCommas(item.count)+`" onkeyup="getRowTotal(this);"></div>
                <div class="col-3 fw-bold"><input type="text" class="form-control text-center cont-total text-danger" value="`+numberWithCommas(item.total)+`" readonly></div>
            </div>`;
            }else{
                //정상계약건
                contTotal += item.total;
                tag += `
            <div class="d-flex flex-nowrap text-center align-items-center">              
                <div class="col-4 col-md-4 fw-bold"><input type="text" class="form-control text-center cont-title" value="${item.prodTitle}"></div>
                <div class="col-3 fw-bold"><input type="text" class="form-control text-center cont-supply" value="`+numberWithCommas(item.applyPrice)+`" onkeyup="getRowTotal(this);"></div>
                <div class="col-1 fw-bold"><input type="text" class="form-control text-center cont-count" value="`+numberWithCommas(item.count)+`" onkeyup="getRowTotal(this);"></div>
                <div class="col-3 fw-bold"><input type="text" class="form-control text-center cont-total" value="`+numberWithCommas(item.total)+`" readonly></div>
            </div>`;
            }
        });
        document.querySelector('#corp-cont-wrap').innerHTML = tag;
        document.querySelector('#corp-total-price-wrap').innerHTML = `<hr><div class="justify-content-end d-flex my-5 text-end"><div class="col-1 fw-bold">전체총액</div><div class="col-2 fw-bold">`
            +numberWithCommas(contTotal)+`</div></div>`;
    }
}