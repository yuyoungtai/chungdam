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