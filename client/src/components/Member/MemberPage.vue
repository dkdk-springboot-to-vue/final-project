<template>
    <div class="my-page">
        <div class="buttons-container">
            <button class="nav-button button-all">Profile</button>
            <button class="nav-button button-all">Saved</button>
            <button class="nav-button button-all">Chat</button>
        </div>
        <div class="card">
            <img :src="profile.imageUrl" class="avatar" alt="프로필사진">

            <div class="form-container">
                <h1>{{ userName }}</h1>
                <label for="userId"><b>ID</b></label>
                {{ profile.userId }}
                <label for="userPw"><b>Password</b></label>
                {{ profile.userPw }}
                <label for="email"><b>Email</b></label>
                {{ profile.email }}
            </div>

            <button @click="contact" class="button-all">수정하기</button>
        </div>
    </div>
</template>


<script>
import { ref } from 'vue';

export default {
    setup() {
        const userName = ref('현진');

        const profile = ref({
            userId: 'ssafy',
            userPw: '1234',
            email: 'ssafy@ssafy.com',
            imageUrl: require('@/assets/img/velkoz.jpg'),
        });

        const contact = () => {
            // Handle contact logic
            console.log('Modify button clicked');
        };

        return { userName, profile, contact };
    },
};
</script>

<style scoped>
.my-page {
    display: flex;
    align-items: center;
}

.buttons-container {
    margin: 0 20px 8px 40px;
    text-align: center;
}

.nav-button {
    margin-bottom: 10px;
    color: white;
    background-color: #000;
    text-align: center;
    cursor: pointer;
    font-size: 18px;
}

.button-all {
    border-radius: 5px;
}

.card {
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    max-width: 300px;
    text-align: center;
    font-family: arial;
    padding: 20px;
    margin: 40px auto;
    /* justify-content: center;
    display: block; */
}

.title {
    color: grey;
    font-size: 18px;
}

.form-container {
    margin: 24px 0;
    justify-content: center;
}

label {
    display: block;
    margin-bottom: 5px;
}

input {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    box-sizing: border-box;
}

button {
    border: none;
    outline: 0;
    display: inline-block;
    padding: 8px;
    color: white;
    background-color: #000;
    text-align: center;
    cursor: pointer;
    width: 100%;
    font-size: 18px;
}

button:hover {
    opacity: 0.7;
}

/* Avatar image styling */
img.avatar {
    width: 40%;
    border-radius: 50%;
    display: block;
    margin: 0 auto;
}
</style>