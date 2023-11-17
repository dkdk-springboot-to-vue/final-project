<template>
    <div>
        <div class="topnav">
            <!-- Home Link -->
            <router-link to="/" :class="{ active: isRouteActive('/') }">Home</router-link>

            <!-- Map Link -->
            <router-link to="/" :class="{ active: isRouteActive('/') }">Map</router-link>


            <!-- Spacer to push Login and Register to the right -->
            <div class="spacer"></div>

            <!-- Login Link -->
            <router-link to="/MemberLogin" :class="{ active: isRouteActive('/MemberLogin') }">Login</router-link>

            <!-- Register Link -->
            <router-link to="/MemberRegister" :class="{ active: isRouteActive('/MemberRegister') }">Register</router-link>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
import { useRoute } from 'vue-router';

export default {
    setup() {
        const route = useRoute();

        const isRouteActive = (path) => {
            // Check if the current route path starts with the specified path
            return route.path.startsWith(path);
        };

        return {
            isRouteActive,
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
    background-color: #2089EF;
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
    background-color: #04AA6D;
    color: white;
}
</style>