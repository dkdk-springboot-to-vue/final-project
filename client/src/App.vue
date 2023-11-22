<script setup>
import { RouterView } from 'vue-router';
import VTopNavigation from './components/common/VTopNavigation.vue';
</script>

<template>
  <div>
    <VTopNavigation />
  </div>
  <router-view></router-view>
</template>

<style scoped></style>
