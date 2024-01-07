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
    maxFileSize: 1048576
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
            }
        });
    }catch (e) {
        alert('디렉팅 파일 가져오기 오류: '+e);
    }
}