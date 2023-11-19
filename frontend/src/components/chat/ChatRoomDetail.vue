<script setup>
import Stomp from 'webstomp-client';
import SockJs from 'sockjs-client';

import { ref, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import { detailRoom } from '@/api/chat';

const route = useRoute();
const router = useRouter();

const { roomid } = route.params;

const chatroom = ref({});

const chat = ref({
  roomId: roomid,
  content: '',
  userId: 'ssafy',
});

onMounted(() => {
  getChatRoom();
  connection(); // 컴포넌트가 마운트되면 소켓 연결 시도
});

const getChatRoom = () => {
  console.log('get detail chatroom');
  detailRoom(roomid, ({ data }) => {
    console.log(data);
    chatroom.value = data;
  });
};

// websocket
const userName = ref('');
const message = ref('');
const recvList = ref([]);
let stompClient = null;
let connected = false;

const send = () => {
  console.log('Send message:' + message.value);
  if (stompClient && stompClient.connected) {
    const msg = {
      userName: userName.value,
      content: message.value,
    };
    stompClient.send('/receive', JSON.stringify(msg), {});
  }
};

const connection = () => {
  const serverURL = 'http://localhost:80/ws';
  let socket = new SockJs(serverURL);
  stompClient = Stomp.over(socket);
  console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
  stompClient.connect(
    {},
    (frame) => {
      connected = true;
      console.log('소켓 연결 성공', frame);
      // stompClient.subscribe('/send', (res) => {
      //   console.log('구독으로 받은 메시지 입니다.', res.body);
      //   recvList.value.push(JSON.parse(res.body));
      // });
    },
    (error) => {
      console.log('소켓 연결 실패', error);
      connected = false;
    }
  );
};
</script>

<template>
  <div></div>
</template>

<style scoped></style>
