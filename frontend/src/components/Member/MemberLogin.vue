<template>
    <button @click="toggleLoginForm" style="width:auto;">Login</button>

    <form @submit.prevent="login" v-if="isLoginFormVisible" class="modal" @click="closeLoginForm">
        <div class="modal-content animate" @click.stop>
            <div class="imgcontainer">
                <span @click="closeLoginForm" class="close" title="Close Modal"></span>
                <img src="@/assets/img/velkoz.jpg" alt="Avatar" class="avatar">
            </div>

            <div class="form-container">
                <!-- user name -->
                <label for="userName"><b>Username</b></label>
                <input v-model="formData.userId" type="text" placeholder="Enter Username" name="userName" required>

                <!-- user password-->
                <label for="userPw"><b>Password</b></label>
                <input v-model="formData.userPw" type="password" placeholder="Enter Password" name="userPw" required>

                <!-- remember check box-->
                <div class="remember-me">
                    <input type="checkbox" v-model="formData.remember">Remember me
                </div>

                <!-- button-->
                <div class="buttons">
                    <button type="submit" @click="login">Login</button>
                    <button type="button" @click="closeLoginForm" class="cancelbtn">Cancel</button>
                </div>
            </div>

            <div class="container" style="background-color:#f1f1f1">
                <span class="pw">Forgot <a href="#">password?</a></span>
            </div>
        </div>
    </form>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
    setup() {
        const isLoginFormVisible = ref(false);
        const formData = ref({
            userId: '',
            userPw: '',
            remember: false,
        });

        const toggleLoginForm = () => {
            isLoginFormVisible.value = !isLoginFormVisible.value;
        };

        const closeLoginForm = () => {
            isLoginFormVisible.value = false;
        };

        const login = async () => {
    try {
        const response = await axios.post('http://localhost:80/api/member/login', {
            auth: {
                userId: formData.userId,
                userPw: formData.userPw,
            }
        });

        // Check if response is defined
        if (response) {
            // 로그인 성공
            console.log('Login successful', response.data);
            alert('로긴 성공 !');

            // 성공적으로 로그인했을 때의 동작 추가
        } else {
            console.error('Empty response received');
            // Handle the case where the response is undefined
            alert('로긴 실패 ㅜㅜ');
        }
    } catch (error) {
        console.error('Error during login', error.response ? error.response.data : error.message);
        // 로그인 실패
        alert('로긴 실패 ㅜㅜ');
    }

    closeLoginForm();
};


        return {
            isLoginFormVisible,
            formData,
            toggleLoginForm,
            closeLoginForm,
            login,
        };
    },
};
</script>

<style scoped>
/* Container for the component */
.container {
    text-align: center;
}

/* Login button styling */
button {
    background-color: salmon;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: auto;
    font-size: 16px;
}

button:hover {
    opacity: 0.8;
}

/* Modal styles */
.modal {
    display: flex;
    /* Use flex to center the modal content */
    justify-content: center;
    align-items: center;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.4);
}

/* Modal content box */
.modal-content {
    background-color: #fefefe;
    padding: 16px;
    /* Add padding to modal content */
    border: 1px solid #888;
    width: 60%;
    max-width: 400px;
    border-radius: 10px;
    text-align: left;
    /* Align text to the left */
}

/* Close button styling */
.close {
    position: absolute;
    right: 25px;
    top: 0;
    color: #000;
    font-size: 35px;
    font-weight: bold;
    cursor: pointer;
}

.close:hover,
.close:focus {
    color: red;
}

/* Image container styling */
.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
    position: relative;
}

/* Avatar image styling */
img.avatar {
    width: 40%;
    border-radius: 50%;
}

/* Form container styling */
.form-container {
    padding: 16px;
}

/* Form label styling */
label {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 8px;
    color: #333;
}

/* Form input styling */
input {
    width: 100%;
    padding: 12px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    border-radius: 5px;
}

/* Remember me checkbox styling */
label input[type="checkbox"] {
    margin-top: 8px;
}

/* Positioning of Remember me and buttons */
.remember-me {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
}


/* Common button styling for submit and cancel */
button[type="submit"]:hover,
.cancelbtn:hover {
    opacity: 0.8;
}

.buttons {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.buttons button {
    width: 48%;
    /* Adjust width as needed */
    width: 100%;
    padding: 10px 18px;
    background-color: #f44336;
    border: none;
    border-radius: 5px;
    color: white;
    cursor: pointer;
    margin-bottom: 8px;
}
</style>
