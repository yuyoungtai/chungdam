//신규 상담 등록 모달
const showAddModal = () => {
    const addModal = new bootstrap.Modal(document.querySelector('#add-consuling-modal'));
    addModal.show();
    initAddModal();
}

//신규 상담 등록
const saveVisit = async () => {
    let type = '';
    if (document.querySelector('input[name="consulting-type"]:checked').attributes.id.value === 'type6') {
        type = document.querySelector('#etc-type').value;
    } else {
        type = document.querySelector('input[name="consulting-type"]:checked').attributes.title.value;
    }
    try {
        await axios.post('/addVisit', {
            visitDate: document.querySelector('#visit-date').value,
            visitTime: document.querySelector('#visit-time').value,
            visitType: type,
            guestName: document.querySelector('#guest-name').value,
            guestHp: document.querySelector('#guest-hp').value,
            eventDate: document.querySelector('#event-date').value,
            person: document.querySelector('#person').value,
            checkMemo: document.querySelector('#check-memo').value,
            path: document.querySelector('#path').value,
            masterName: document.querySelector('#master-name').value,
            masterDate: document.querySelector('#master-date').value,
            memo: document.querySelector('#etc-memo').value,
        }).then(response => {
            alert('등록 완료!');
            self.location.reload();
        });
    } catch (e) {
        alert('신규 상담 등록 오류: ' + e);
    }
}

//기타 상담 클릭 리스너
document.querySelectorAll('input[name="consulting-type"]').forEach(item => {
    item.addEventListener('change', (e) => {
        if (e.target.attributes.id.value === 'type6') {
            document.getElementById('etc-type').readOnly = false;
        } else {
            document.getElementById('etc-type').readOnly = true;
        }
    });
});

const initAddModal = () => {
    document.querySelector('#visit-date').value = '';
    document.querySelector('#visit-time').value = '';
    document.querySelector('#type1').checked = true;
    document.querySelector('#guest-name').value = '';
    document.querySelector('#guest-hp').value = '';
    document.querySelector('#event-date').value = '';
    document.querySelector('#person').value = '';
    document.querySelector('#check-memo').value = '';
    document.querySelector('#path').value = '';
    document.querySelector('#master-name').value = '';
    document.querySelector('#master-date').value = '';
    document.querySelector('#etc-memo').value = '';
}

const initEditModal = () => {
    document.querySelector('#edit-visit-date').value='';
    document.querySelector('#edit-visit-time').value = '';
    document.querySelector('#edit-type1').checked = true;
    document.querySelector('#edit-guest-name').value = '';
    document.querySelector('#edit-guest-hp').value = '';
    document.querySelector('#edit-event-date').value = '';
    document.querySelector('#edit-person').value = '';
    document.querySelector('#edit-check-memo').value = '';
    document.querySelector('#edit-path').value = '';
    document.querySelector('#edit-master-name').value = '';
    document.querySelector('#edit-master-date').value = '';
    document.querySelector('#edit-etc-memo').value = '';
}

//수정 버튼
const editBtn = async (visitId) => {
    try {
        await axios.post('/findVisitInfoById', {
            visitId
        }).then(response => {
            const editModal = new bootstrap.Modal(document.querySelector('#edit-consuling-modal'));
            editModal.show();

            initEditModal();
            printEditVisitInfo(response.data);
        });
    } catch (e) {
        alert('정보 로딩 오류: ' + e);
    }
}

const printEditVisitInfo = (visit) => {
    document.querySelector('#edit-id').value = visit.visitId;
    document.querySelector('#edit-visit-date').value = visit.visitDate;
    document.querySelector('#edit-visit-time').value = visit.visitTime;
    if(visit.visitType === '일반상담'){
        document.querySelector('#edit-type1').checked = true;
        document.getElementById('edit-etc-type').readOnly = true;
    }else if(visit.visitType === '본식상담'){
        document.querySelector('#edit-type2').checked = true;
        document.getElementById('edit-etc-type').readOnly = true;
    }else if(visit.visitType === '시식상담'){
        document.querySelector('#edit-type3').checked = true;
        document.getElementById('edit-etc-type').readOnly = true;
    }else if(visit.visitType === '본식 ZOOM'){
        document.querySelector('#edit-type4').checked = true;
        document.getElementById('edit-etc-type').readOnly = true;
    }else if(visit.visitType === '1주년'){
        document.querySelector('#edit-type5').checked = true;
        document.getElementById('edit-etc-type').readOnly = true;
    }else{
        document.querySelector('#edit-type6').checked = true;
        document.getElementById('edit-etc-type').readOnly = false;
        document.getElementById('edit-etc-type').value = visit.visitType;
    }

    document.querySelector('#edit-guest-name').value = visit.guestName;
    document.querySelector('#edit-guest-hp').value = visit.guestHp;
    document.querySelector('#edit-event-date').value = visit.eventDate;
    document.querySelector('#edit-person').value = visit.person;
    document.querySelector('#edit-check-memo').value = visit.checkMemo;
    document.querySelector('#edit-path').value = visit.path;
    document.querySelector('#edit-master-name').value = visit.masterName;
    document.querySelector('#edit-master-date').value = visit.masterDate;
    document.querySelector('#edit-etc-memo').value = visit.memo;
}

//방문 정보 수정
const updateVisit = async () => {
    let type = '';
    if (document.querySelector('input[name="edit-consulting-type"]:checked').attributes.id.value === 'type6') {
        type = document.querySelector('#edit-etc-type').value;
    } else {
        type = document.querySelector('input[name="edit-consulting-type"]:checked').attributes.title.value;
    }
    try {
        await axios.post('/updateVisit', {
            visitId: document.querySelector('#edit-id').value,
            visitDate: document.querySelector('#edit-visit-date').value,
            visitTime: document.querySelector('#edit-visit-time').value,
            visitType: type,
            guestName: document.querySelector('#edit-guest-name').value,
            guestHp: document.querySelector('#edit-guest-hp').value,
            eventDate: document.querySelector('#edit-event-date').value,
            person: document.querySelector('#edit-person').value,
            checkMemo: document.querySelector('#edit-check-memo').value,
            path: document.querySelector('#edit-path').value,
            masterName: document.querySelector('#edit-master-name').value,
            masterDate: document.querySelector('#edit-master-date').value,
            memo: document.querySelector('#edit-etc-memo').value,
        }).then(response => {
            alert('수정 완료!');
            self.location.reload();
        });
    } catch (e) {
        alert('정보 업데이트 오류: ' + e);
    }
}

const delModal = () => {
    const delModal = new bootstrap.Modal(document.querySelector('#del-confirm-modal'));
    delModal.show();
    document.querySelector('#del-id').value = document.querySelector('#edit-id').value;
}

//방문 삭제
const delVisit = async () => {
    try{
        await axios.post('/delVisit',{
            visitId : document.querySelector('#del-id').value
        }).then(response => {
           alert('삭제되었습니다.');
           self.location.reload();
        });
    }catch (e) {
        alert('삭제 오류: '+e);
    }
}

//프린드 페이지로 새창 띄우기
const printPage = () => {
    window.open('/consultingPrintView', '_blank');
}