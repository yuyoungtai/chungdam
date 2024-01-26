const editUserModal = async (userId) => {
    try{
        await axios.post('/findUserInfoById', {
            userId
        }).then(response => {
            const userModal = new bootstrap.Modal(document.querySelector('#user-modal'));
            userModal.show();

            document.querySelector('#user-email').value = response.data.email;
            document.querySelector('#user-name').value = response.data.name;
            document.querySelector('#user-hp').value = response.data.hp;
            document.querySelector('#user-id').value = response.data.userId;
        });
    }catch (e) {
        alert('사용자 정보 가져오기 오류: '+e);
    }
}


const updateUserInfo = async () => {
    try{
        await axios.post('/updateUserInfo',{
            userId: document.querySelector('#user-id').value,
            email: document.querySelector('#user-email').value,
            name: document.querySelector('#user-name').value,
            hp: document.querySelector('#user-hp').value
        }).then(response => {
            alert('수정 완료!');
            self.location.reload();
        })
    }catch (e) {
        alert('고객정보 수정 오류: '+e);
    }
}

const delUserModal = () =>{
    const delModal = new bootstrap.Modal(document.querySelector('#del-modal'));
    delModal.show();
    document.querySelector('#del-id').value = document.querySelector('#user-id').value;
}

const delMember = async () => {
    try{
        await axios.post('/delUser', {
            userId: document.querySelector('#del-id').value
        }).then(response => {
           alert('삭제 완료!');
           self.location.reload();
        });
    }catch (e) {
        alert('삭제 오류: '+e);
    }
}