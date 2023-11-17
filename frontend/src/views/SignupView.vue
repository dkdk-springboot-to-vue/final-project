<template>
    <div class="body">
        <!-- Email row -->
        <FormRow label="email" v-model="email" :error="errorEmail" />

        <!-- Password row -->
        <FormRow label="password" type="password" v-model="password" :error="errorPassword" />

        <!-- Password confirmation row -->
        <FormRow label="repeat password" type="password" v-model="passwordConfirm" :error="errorPasswordConfirm" />

        <!-- Signup button row -->
        <div class="row">
            <button @click="register" style="margin-top: 32px">Register</button>
        </div>
    </div>
</template>

<script>
import FormRow from './FormRow.vue';

export default {
    components: {
        FormRow,
    },
    data() {
        return {
            email: '',
            password: '',
            passwordConfirm: '',
            errorEmail: '이메일 규칙에 맞지 않습니다.',
            errorPassword: '비밀번호는 6자 이상 입력해주세요.',
            errorPasswordConfirm: '비밀번호가 일치하지 않습니다.',
        };
    },
    methods: {
        register() {
            // Add your registration logic here
            console.log('Registration logic goes here');
        },
    },
};
</script>

<style scoped>
.body {
    padding: 16px;
}

.row {
    width: 500px;
    height: 80px;
    word-break: break-all;
    word-wrap: break-word;
}

.left {
    width: 120px;
    height: 36px;
    padding: 8px;
    float: left;
    display: flex;
    justify-content: center;
    align-items: center;
    box-sizing: border-box;
    background: #9FA8DA;
}

.right {
    width: 380px;
    height: 36px;
    padding: 8px;
    float: left;
    display: flex;
    align-items: center;
    box-sizing: border-box;
    background: #C5CAE9;
}

.error-text {
    margin-left: 130px;
    font-size: small;
    color: red;
}
</style>