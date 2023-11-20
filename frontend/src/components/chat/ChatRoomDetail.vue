<script setup>
import Stomp from 'webstomp-client';
import SockJs from 'sockjs-client';
import * as StompJs from '@stomp/stompjs';

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
  // connection(); // 컴포넌트가 마운트되면 소켓 연결 시도
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

const client = new StompJs.Client({
  brokeURL: `ws://localhost:80/ws`,
  debug: function (str) {
    console.log(str);
  },
});

const connection = () => {
  const serverURL = 'http://localhost:80/ws';
  let socket = new SockJs(serverURL);
  stompClient = Stomp.over(socket);
  console.log(stompClient);
  console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
  stompClient.connect(
    {},
    (frame) => {
      connected = true;
      console.log('소켓 연결 성공', frame);
      stompClient.subscribe('/sub/chat/room/1', (res) => {
        console.log('구독으로 받은 메시지 입니다.', res.body);
        recvList.value.push(JSON.parse(res.body));
      });
    },
    (error) => {
      console.log('소켓 연결 실패', error);
      connected = false;
    }
  );
};

// const connect = () => {
//   // 소켓 연결
//   try {
//     const clientdata = new StompJs.Client({
//       brokerURL: 'ws://localhost:80/ws',
//       debug: function (str) {
//         console.log(str);
//       },
//       reconnectDelay: 5000, // 자동 재 연결
//       heartbeatIncoming: 4000,
//       heartbeatOutgoing: 4000,
//     });

//     // 구독
//     clientdata.onConnect = function () {
//       clientdata.subscribe('/sub/chat/1', callback);
//     };

//     clientdata.activate(); // 클라이언트 활성화
//     changeClient(clientdata); // 클라이언트 갱신
//   } catch (err) {
//     console.log(err);
//   }
// };
</script>

<template>
  <div>
    <button @click="connection()">버튼</button>
  </div>
</template>

<style scoped></style>
