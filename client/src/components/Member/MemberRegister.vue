<script>
import { ref } from 'vue';
import axios from 'axios';
import { validateId, validatePw, validateEmail } from '@/util/validation';
import router from '@/router';

export default {
  setup() {
    const formData = ref({
      userId: '',
      userName: '',
      email: '',
      userPw: '',
      passwordRepeat: '',
    });

    const register = async () => {
      // 유효성 검사
      if (!validateId(formData.value.userId)) {
        alert('올바른 아이디 형식이 아닙니다.');
        return;
      }

      const pwValidation = validatePw(formData.value.userPw);
      if (!pwValidation[0]) {
        alert(pwValidation[1]);
        return;
      }

      if (!validateEmail(formData.value.email)) {
        alert('올바른 이메일 형식이 아닙니다.');
        return;
      }

      if (formData.value.userPw !== formData.value.passwordRepeat) {
        console.error('Passwords do not match');
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }

      try {
        const response = await axios.post('http://192.168.205.92:80/api/member', {
          userId: formData.value.userId,
          userName: formData.value.userName,
          email: formData.value.email,
          userPw: formData.value.userPw,
        });

        console.log('Registration successful', response.data);
        alert('회원가입 성공!');
        router.push('/Member/login');
      } catch (error) {
        console.error('Error registering', error);
        if (error.response) {
          console.error('Server responded with:', error.response.data);
        } else if (error.request) {
          console.error('No response received');
        } else {
          console.error('Error before sending request', error.message);
        }

        alert('이미 존재하는 회원입니다.');
      }
    };

    return {
      formData,
      register,
    };
  },
};
</script>

<template>
  <form @submit.prevent="register">
    <div class="container">
      <h1>Register</h1>
      <p>Please fill in this form to create an account.</p>
      <hr />

      <label for="userId"><b>User ID</b></label>
      <input
        type="text"
        v-model="formData.userId"
        placeholder="Enter User ID"
        name="userId"
        id="userId"
        required
      />

      <label for="userName"><b>User Name</b></label>
      <input
        type="text"
        v-model="formData.userName"
        placeholder="Enter User Name"
        name="userName"
        id="userName"
        required
      />

      <label for="email"><b>Email</b></label>
      <input
        type="text"
        v-model="formData.email"
        placeholder="Enter Email"
        name="email"
        id="email"
        required
      />

      <label for="userPw"><b>Password</b></label>
      <input
        type="password"
        v-model="formData.userPw"
        placeholder="Enter Password"
        name="userPw"
        id="userPw"
        required
      />

      <label for="passwordRepeat"><b>Repeat Password</b></label>
      <input
        type="password"
        v-model="formData.passwordRepeat"
        placeholder="Repeat Password"
        name="passwordRepeat"
        id="passwordRepeat"
        required
      />
      <hr />

      <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
      <button type="submit" class="registerbtn">Register</button>
    </div>

    <div class="container signin">
      <p>Already have an account? <router-link to="/MemberLogin">Sign in</router-link>.</p>
    </div>
  </form>
</template>

<style scoped>
* {
  box-sizing: border-box;
}

.container {
  text-align: center;
  max-width: 30%;
  /* Set maximum width */
  margin: auto;
  /* Center the container */
  padding: 16px;
  /* Adjust padding for layout */
}

input[type='text'],
input[type='password'] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type='text']:focus,
input[type='password']:focus {
  background-color: #ddd;
  outline: none;
}

hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

.registerbtn {
  background-color: #f44336;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
  margin-bottom: 8px;
}

.registerbtn:hover {
  opacity: 1;
}

a {
  color: dodgerblue;
}

.signin {
  background-color: #f1f1f1;
  text-align: center;
}
</style>
