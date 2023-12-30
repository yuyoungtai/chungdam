const findEmail = async () =>{
    const findHp = getNumberAbsolute(document.querySelector('#find-hp').value);
    if(findHp === ''){
        alert('가입하신 휴대폰 번호를 입력하세요.');
    }else{
        try{
            await axios.post('/findSignupEmail',{
                hp: findHp
            }).then(response => {
                if(response.data === 'noData'){
                    document.querySelector('#result-wrap').innerHTML = '정보를 찾을 수 없습니다.';
                }else{
                    document.querySelector('#result-wrap').innerHTML = '가입하신 이메일: '+response.data;
                }
            })
        }catch (e) {
            alert('아이디 찾기 오류: '+e);
        }
    }
}

const findPass = async () =>{
    const findHp = getNumberAbsolute(document.querySelector('#find-pw-hp').value);
    if(findHp === ''){
        alert('가입하신 휴대폰 번호를 입력하세요.');
    }else{
        try{
            await axios.post('/findSignupPass',{
                hp: findHp
            }).then(response => {
                if(response.data === 'noData'){
                    document.querySelector('#result-wrap').innerHTML = '정보를 찾을 수 없습니다.';
                }else{
                    document.querySelector('#result-pw-wrap').innerHTML = '비밀번호: '+response.data;
                }
            })
        }catch (e) {
            alert('아이디 찾기 오류: '+e);
        }
    }
}