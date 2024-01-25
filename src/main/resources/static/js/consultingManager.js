//신규 상담 등록 모달
const showAddModal = () => {
    const addModal = new bootstrap.Modal(document.querySelector('#add-consuling-modal'));
    addModal.show();

}

//신규 상담 등록
const saveVisit = async () => {
    let type = '';
    if(document.querySelector('input[name="consulting-type"]:checked').attributes.id.value === 'type6'){
        type = document.querySelector('#etc-type').value;
    }else{
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
       if(e.target.attributes.id.value === 'type6'){
           document.getElementById('etc-type').readOnly=false;
       }else{
           document.getElementById('etc-type').readOnly=true;
       }
    });
});