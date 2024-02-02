let initBodyHtml = '';

//숫자에 콤마 찍기
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//문자열에서 숫자만 추출.(-)음수를 위해 예외처리
function getNumber(x) {
    return x.toString().replace(/[^0-9^-]/g, '');
}

//문자열에서 숫자만 추출.(-)음수를 위해 예외처리
function getNumberAbsolute(x) {
    return x.toString().replace(/[^0-9]/g, '');
}

//휴대폰 번호 형식 리턴
function getHpNumber(x) {
    return x.toString().replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
}

const beforePrint = () => {
    initBodyHtml = document.body.innerHTML;
    document.body.innerHTML = document.querySelector('#print-area').innerHTML;
}

const print = () => {
    alert('출력후 화면을 새로고침하면 정상으로 보입니다.');
    window.onbeforeprint = beforePrint();
    window.print();
}