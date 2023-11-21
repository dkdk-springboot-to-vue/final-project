import { createRouter, createWebHistory } from 'vue-router';
// import Home from "../views/Home.vue";
import MemberLogin from '../components/Member/MemberLogin.vue';
import MemberRegister from '../components/Member/MemberRegister.vue';

const routes = [
  {
path: '/',
    name: 'Home',
    // component: Home,
  },
  {
    path: '/MemberLogin',
    name: 'MemberLogin',
    component: MemberLogin,
  },
  {
    path: '/MemberRegister', 
    name: 'MemberRegister',
    component: MemberRegister,
  },
{
    path: '/attractions',
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
