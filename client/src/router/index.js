import { createRouter, createWebHistory } from 'vue-router';
// import Home from "../views/Home.vue";
import MemberLogin from '../components/Member/MemberLogin.vue';
import MemberRegister from '../components/Member/MemberRegister.vue';
import MemberPage from '../components/Member/MemberPage.vue';

const routes = [
  // {
  //   path: '/MemberLogin',
  //   name: 'MemberLogin',
  //   component: MemberLogin,
  // },
  // {
  //   path: '/MemberRegister',
  //   name: 'MemberRegister',
  //   component: MemberRegister,
  // },
  // {
  //   path: '/MemberPage',
  //   name: 'MemberPage',
  //   component: MemberPage,
  // },

  // hyunjin
  {
    path: '/Member',
    name: 'member',
    component: () => import('../views/TheMemberView.vue'),
    redirect: { name: 'member-register' },
    children: [
      {
        path: 'register',
        name: 'member-register',
        component: () => import('@/components/Member/MemberRegister.vue'),
      },
      {
        path: 'login',
        name: 'member-login',
        component: () => import('@/components/Member/MemberLogin.vue'),
      },
      {
        path: 'find',
        name: 'member-page',
        component: () => import('@/components/Member/MemberPage.vue'),
      },
      // {
      //   path: "find",
      //   name: "member-page",
      //   component: () => import("@/components/Member/MemberFind.vue"),
      // },
    ],
  },

  {
    path: '/board',
    name: 'board',
    component: () => import('../views/TheBoardView.vue'),
    redirect: { name: 'article-list' },
    children: [
      {
        path: 'list',
        name: 'article-list',
        component: () => import('@/components/board/BoardList.vue'),
      },
      {
        path: 'view/:articleno',
        name: 'article-view',
        component: () => import('@/components/board/BoardDetail.vue'),
      },
      {
        path: 'write',
        name: 'article-write',
        component: () => import('@/components/board/BoardWrite.vue'),
      },
      {
        path: 'modify/:articleno',
        name: 'article-modify',
        component: () => import('@/components/board/BoardModify.vue'),
      },
    ],
  },

  {
    path: '/',
    name: 'attractions',
    component: () => import('@/views/TheAttractionView.vue'),
    redirect: { name: 'attraction-list' },
    children: [
      {
        path: 'list',
        name: 'attraction-list',
        component: () => import('@/components/attraction/AttractionList.vue'),
      },
    ],
  },
  {
    path: '/chat',
    name: 'chat',
    component: () => import('@/views/TheChatView.vue'),
    children: [
      {
        path: 'list',
        name: 'chatroom-list',
        component: () => import('@/components/chat/ChatRoomList.vue'),
      },
      {
        path: 'detail/:roomid',
        name: 'detail-chatroom',
        component: () => import('@/components/chat/ChatRoomDetail.vue'),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
