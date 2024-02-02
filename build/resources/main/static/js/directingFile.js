//웨딩팀 기획서 업로드 리스너
$('#wplan').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            type: 'wplan',
            eventId: document.querySelector('#sel-event-id').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#sel-event-id').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});

//플라워팀 기획서 업로드 리스너
$('#fplan').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            type: 'fplan',
            eventId: document.querySelector('#sel-event-id').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#sel-event-id').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});

//조리팀 기획서 업로드 리스너
$('#cplan').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            type: 'cplan',
            eventId: document.querySelector('#sel-event-id').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#sel-event-id').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});

//연회팀 기획서 업로드 리스너
$('#dplan').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            type: 'dplan',
            eventId: document.querySelector('#sel-event-id').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#sel-event-id').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});

//행사 진행표 업로드 리스너
$('#summary').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            type: 'summary',
            eventId: document.querySelector('#sel-event-id').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#sel-event-id').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});

//정산서 업로드 리스너
$('#bill').fileinput({
    uploadUrl: '/directingUpload',
    uploadExtraData:function (previewId, index){
        const data = {
            type: 'bill',
            eventId: document.querySelector('#sel-event-id').value
        };
        return data;
    },
    maxFileSize: 1073741824
}).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
    directingFileCheck(document.querySelector('#sel-event-id').value);
}).on('fileselect', function (event, numFiles, label){
    const fileSize = event.target.files[0].size;
    if(fileSize > 1073741824){
        alert('1기가 이하의 파일만 업로드 할 수 있습니다.');
    }
});


//디렉팅 파일 가져오기
const directingFileCheck = async (eventId) => {
    try{
        await axios.post('/getDirectingFile', {
            eventId
        }).then(response => {

            if(response.data.length === 0){
                document.querySelector('#wplan-switch').checked = false;
                document.querySelector('#wplan-down-btn1').removeAttribute('src');
                document.querySelector('#wplan-down-btn2').removeAttribute('src');

                document.querySelector('#fplan-switch').checked = false;
                document.querySelector('#fplan-down-btn1').removeAttribute('src');
                document.querySelector('#fplan-down-btn2').removeAttribute('src');

                document.querySelector('#cplan-switch').checked = false;
                document.querySelector('#cplan-down-btn1').removeAttribute('src');
                document.querySelector('#cplan-down-btn2').removeAttribute('src');

                document.querySelector('#dplan-switch').checked = false;
                document.querySelector('#dplan-down-btn1').removeAttribute('src');
                document.querySelector('#dplan-down-btn2').removeAttribute('src');

                document.querySelector('#summary-switch').checked = false;
                document.querySelector('#summary-down-btn1').removeAttribute('src');
                document.querySelector('#summary-down-btn2').removeAttribute('src');

                document.querySelector('#bill-switch').checked = false;
                document.querySelector('#bill-down-btn1').removeAttribute('src');
                document.querySelector('#bill-down-btn2').removeAttribute('src');
            }else{
                if(response.data.wplan === null){
                    document.querySelector('#wplan-switch').checked = false;
                    document.querySelector('#wplan-down-btn1').removeAttribute('src');
                    document.querySelector('#wplan-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#wplan-switch').checked = true;
                    document.querySelector('#wplan-down-btn1').setAttribute('src', response.data.wplan);
                    document.querySelector('#wplan-down-btn2').setAttribute('src', response.data.wplan);
                }
                if(response.data.fplan === null){
                    document.querySelector('#fplan-switch').checked = false;
                    document.querySelector('#fplan-down-btn1').removeAttribute('src');
                    document.querySelector('#fplan-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#fplan-switch').checked = true;
                    document.querySelector('#fplan-down-btn1').setAttribute('src', response.data.fplan);
                    document.querySelector('#fplan-down-btn2').setAttribute('src', response.data.fplan);
                }
                if(response.data.cplan === null){
                    document.querySelector('#cplan-switch').checked = false;
                    document.querySelector('#cplan-down-btn1').removeAttribute('src');
                    document.querySelector('#cplan-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#cplan-switch').checked = true;
                    document.querySelector('#cplan-down-btn1').setAttribute('src', response.data.cplan);
                    document.querySelector('#cplan-down-btn2').setAttribute('src', response.data.cplan);
                }
                if(response.data.dplan === null){
                    document.querySelector('#dplan-switch').checked = false;
                    document.querySelector('#dplan-down-btn1').removeAttribute('src');
                    document.querySelector('#dplan-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#dplan-switch').checked = true;
                    document.querySelector('#dplan-down-btn1').setAttribute('src', response.data.dplan);
                    document.querySelector('#dplan-down-btn2').setAttribute('src', response.data.dplan);
                }
                if(response.data.summary === null){
                    document.querySelector('#summary-switch').checked = false;
                    document.querySelector('#summary-down-btn1').removeAttribute('src');
                    document.querySelector('#summary-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#summary-switch').checked = true;
                    document.querySelector('#summary-down-btn1').setAttribute('src', response.data.summary);
                    document.querySelector('#summary-down-btn2').setAttribute('src', response.data.summary);
                }
                if(response.data.bill === null){
                    document.querySelector('#bill-switch').checked = false;
                    document.querySelector('#bill-down-btn1').removeAttribute('src');
                    document.querySelector('#bill-down-btn2').removeAttribute('src');
                }else{
                    document.querySelector('#bill-switch').checked = true;
                    document.querySelector('#bill-down-btn1').setAttribute('src', response.data.bill);
                    document.querySelector('#bill-down-btn2').setAttribute('src', response.data.bill);
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