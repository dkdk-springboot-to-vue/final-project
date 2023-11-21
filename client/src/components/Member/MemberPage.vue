<template>
    <div class="my-page">
        <div class="buttons-container">
            <button class="nav-button">Profile</button>
            <button class="nav-button">Saved</button>
            <button class="nav-button">Chat</button>
        </div>
        <div class="card">
            <img :src="profile.imageUrl" class="avatar" alt="프로필사진">
            <h1>{{ userName }}</h1>
            <p class="title">{{ profile.position }}</p>
            <p>{{ profile.education }}</p>

            <div class="form-container">
                <label for="userId"><b>ID</b></label>
                <input v-model="formData.userId" name="userId" id="userId" required>

                <label for="userPw"><b>Password</b></label>
                <input type="password" v-model="formData.userPw" name="userPw" id="userPw" required>

                <label for="email"><b>Email</b></label>
                <input v-model="formData.email" name="email" id="email" required>
            </div>

            <button @click="contact">수정하기</button>
        </div>
    </div>
</template>
  
<script>
import { ref } from 'vue';

export default {
    setup() {
        const userName = ref('현진');

        const profile = ref({
            position: '취준생',
            education: '싸피생',
            imageUrl: require('@/assets/img/velkoz.jpg'),
        });

        const formData = ref({
            userId: '',
            userPw: '',
            email: '',
        });

        const contact = () => {
            // Handle contact logic
            console.log('Modify button clicked');
        };

        return { userName, profile, formData, contact };
    },
};
</script>
  
<style scoped>
.my-page {
    display: flex;
    align-items: center;
}

.buttons-container {
    display: flex;
    flex-direction: column;
    padding: 20px;
    margin: 8px 0;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    width: 100%;
    opacity: 0.9;
    margin-bottom: 8px;
    text-align: center;
}

.nav-button {
    border: none;
    outline: 0;
    display: block;
    padding: 12px;
    /* Increased padding for larger buttons */
    margin-bottom: 10px;
    color: white;
    background-color: #000;
    text-align: center;
    cursor: pointer;
    width: 30%;
    font-size: 18px;
}

.card {
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    max-width: 300px;
    text-align: center;
    font-family: arial;
    padding: 20px;
    justify-content: center;
    display: block;
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