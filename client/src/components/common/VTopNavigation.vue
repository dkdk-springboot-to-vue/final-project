<template>
  <div>
    <div class="topnav">
      <!-- Home Link -->
      <router-link to="/" :class="{ active: isRouteActive('/') }">Home</router-link>

      <!-- Map Link -->
      <router-link :to="{ name: 'attractions' }" :class="{ active: isRouteActive('/') }">Map</router-link>

      <!-- chat link-->
      <router-link :to="{ name: 'chatroom-list' }" :class="{ active: isRouteActive('/') }">chat</router-link>

      <!-- board link-->
      <router-link :to="{ name: 'board' }" class="nav-link">board</router-link>

      <!-- Spacer to push Login and Register to the right -->
      <div class="spacer"></div>

      <!-- <div>{{ userId }}</div> -->

      <!-- myPage Link -->
      <router-link :to="{ name: 'member-page' }" :class="{ active: isRouteActive('/') }">My Page</router-link>

      <!-- Login Link -->
      <router-link :to="{ name: 'member-login' }" :class="{ active: isRouteActive('/') }">Login</router-link>

      <!-- Register Link -->
      <router-link :to="{ name: 'member-register' }" :class="{ active: isRouteActive('/') }">Register</router-link>
    </div>
    <!-- <router-view></router-view> -->
  </div>
</template>

<script>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

export default {
  setup() {
    const route = useRoute();
    const router = useRouter();

    // 세션스토리지
    // const userId = ref('');
    // userId.value = sessionStorage.getItem('user')

    const isRouteActive = (path) => {
      // Check if the current route path starts with the specified path
      return route.path.startsWith(path);
    };

    return {
      isRouteActive,
      // userId,
    };
  },
};
</script>

<style>
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.topnav {
  overflow: hidden;
  background-color: #2089ef;
  display: flex;
  /* Use flexbox to arrange items horizontally */
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav .spacer {
  flex: 1;
  /* Flex grow property to push Login and Register to the right */
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #04aa6d;
  color: white;
}
</style>
