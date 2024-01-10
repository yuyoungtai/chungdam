//기획서 업로드 리스너
$('#plan').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            id: 'plan',
            email: document.querySelector('#email').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#email').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});

//식순 업로드 리스너
$('#siksun').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            id: 'siksun',
            email: document.querySelector('#email').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#email').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});

//사회자 스크립트 업로드 리스너
$('#mc').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            id: 'mc',
            email: document.querySelector('#email').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#email').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});

//혼인서약서 업로드 리스너
$('#honin').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            id: 'honin',
            email: document.querySelector('#email').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#email').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});

//성혼선언문 업로드 리스너
$('#sunghon').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            id: 'sunghon',
            email: document.querySelector('#email').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#email').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});

//기타 업로드 리스너
$('#etc').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            id: 'etc',
            email: document.querySelector('#email').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#email').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});

//디렉팅 파일 가져오기
const directingFileCheck = async (email) => {
    try{
        await axios.post('/getDirectingFile', {
            email
        }).then(response => {

            if(response.data.length === 0){
                document.querySelector('#plan-switch').checked = false;
                document.querySelector('#plan-down-btn1').removeAttribute('src');
                document.querySelector('#plan-down-btn2').removeAttribute('src');

                document.querySelector('#siksun-switch').checked = false;
                document.querySelector('#siksun-down-btn1').removeAttribute('src');
                document.querySelector('#siksun-down-btn2').removeAttribute('src');

                document.querySelector('#mc-switch').checked = false;
                document.querySelector('#mc-down-btn1').removeAttribute('src');
                document.querySelector('#mc-down-btn2').removeAttribute('src');

                document.querySelector('#honin-switch').checked = false;
                document.querySelector('#honin-down-btn1').removeAttribute('src');
                document.querySelector('#honin-down-btn2').removeAttribute('src');

                document.querySelector('#sunghon-switch').checked = false;
                document.querySelector('#sunghon-down-btn1').removeAttribute('src');
                document.querySelector('#sunghon-down-btn2').removeAttribute('src');

                document.querySelector('#etc-switch').checked = false;
                document.querySelector('#etc-down-btn1').removeAttribute('src');
                document.querySelector('#etc-down-btn2').removeAttribute('src');
            }else{
                if(response.data.plan === null){
                    document.querySelector('#plan-switch').checked = false;
                    document.querySelector('#plan-down-btn1').removeAttribute('src');
                    document.querySelector('#plan-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#plan-switch').checked = true;
                    document.querySelector('#plan-down-btn1').setAttribute('src', response.data.plan);
                    document.querySelector('#plan-down-btn2').setAttribute('src', response.data.plan);
                }
                if(response.data.siksun === null){
                    document.querySelector('#siksun-switch').checked = false;
                    document.querySelector('#siksun-down-btn1').removeAttribute('src');
                    document.querySelector('#siksun-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#siksun-switch').checked = true;
                    document.querySelector('#siksun-down-btn1').setAttribute('src', response.data.siksun);
                    document.querySelector('#siksun-down-btn2').setAttribute('src', response.data.siksun);
                }
                if(response.data.mc === null){
                    document.querySelector('#mc-switch').checked = false;
                    document.querySelector('#mc-down-btn1').removeAttribute('src');
                    document.querySelector('#mc-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#mc-switch').checked = true;
                    document.querySelector('#mc-down-btn1').setAttribute('src', response.data.mc);
                    document.querySelector('#mc-down-btn2').setAttribute('src', response.data.mc);
                }
                if(response.data.honin === null){
                    document.querySelector('#honin-switch').checked = false;
                    document.querySelector('#honin-down-btn1').removeAttribute('src');
                    document.querySelector('#honin-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#honin-switch').checked = true;
                    document.querySelector('#honin-down-btn1').setAttribute('src', response.data.honin);
                    document.querySelector('#honin-down-btn2').setAttribute('src', response.data.honin);
                }
                if(response.data.sunghon === null){
                    document.querySelector('#sunghon-switch').checked = false;
                    document.querySelector('#sunghon-down-btn1').removeAttribute('src');
                    document.querySelector('#sunghon-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#sunghon-switch').checked = true;
                    document.querySelector('#sunghon-down-btn1').setAttribute('src', response.data.sunghon);
                    document.querySelector('#sunghon-down-btn2').setAttribute('src', response.data.sunghon);
                }
                if(response.data.etc === null){
                    document.querySelector('#etc-switch').checked = false;
                    document.querySelector('#etc-down-btn1').removeAttribute('src');
                    document.querySelector('#etc-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#etc-switch').checked = true;
                    document.querySelector('#etc-down-btn1').setAttribute('src', response.data.etc);
                    document.querySelector('#etc-down-btn2').setAttribute('src', response.data.etc);
                }
            }
        });
    }catch (e) {
        alert('디렉팅 파일 가져오기 오류: '+e);
    }
}


//파일 다운로드
const downloadFile1 = (self) =>{
    //console.log(self.attributes.src.value);

    var downloadFrame = document.getElementById('invisible');
    downloadFrame.src = `https://chungdam.s3.ap-northeast-2.amazonaws.com/${self.attributes.src.value}`;
}

//파일 다운로드
const downloadFile2 = (self) =>{
    //console.log(self.attributes.src.value);

    const link = document.createElement("a");
    link.setAttribute('target', '_blank');
    link.href = `https://chungdam.s3.ap-northeast-2.amazonaws.com/${self.attributes.src.value}`;
    link.download = "directingFile";
    link.click();
}