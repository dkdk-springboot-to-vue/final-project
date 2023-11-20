<script setup>
import { RouterView } from 'vue-router';
// import SignupView from './views/SignupView.vue';
// import MemberLogin from './components/Member/MemberLogin.vue';
// import MemberRegister from './components/Member/MemberRegister.vue';
import TopNavigation from './components/idk/TopNavigation.vue';
</script>

<template>
  <div>
    <TopNavigation />
  </div>
  <router-view></router-view>
</template>

<style scoped></style>
