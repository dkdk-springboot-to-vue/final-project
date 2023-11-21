<script setup>
import Stomp from 'webstomp-client';
import SockJs from 'sockjs-client';
// import * as StompJs from '@stomp/stompjs';

import { ref, onMounted, watch } from 'vue';
import { useRoute } from 'vue-router';

import { detailRoom } from '@/api/chat';

const route = useRoute();

const { roomid } = route.params;
console.log('roomId : ' + roomid);
const chatroom = ref({});

const chatErrMsg = ref('');

const chat = ref({
  roomId: roomid,
  chatId: 0,
  createdAt: '',
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
const recvList = ref([]);
let stompClient = null;

// eslint-disable-next-line no-unused-vars
let connected = false;

const send = () => {
  console.log('Send message:' + chat.value);
  if (stompClient && stompClient.connected) {
    const msg = chat.value;
    stompClient.send('/pub/chat', JSON.stringify(msg), {});
  }
};

// const client = new StompJs.Client({
//   brokeURL: `ws://localhost:80/ws`,
//   debug: function (str) {
//     console.log(str);
//   },
// });

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
      stompClient.subscribe('/sub/chat/1', (res) => {
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

watch(
  () => chat.value.content,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 100) {
      chatErrMsg.value = '내용을 확인해 주세요!!!';
    } else chatErrMsg.value = '';
  },
  { immediate: true }
);

function onSubmit() {
  if (chatErrMsg.value) {
    alert(chatErrMsg.value);
  } else {
    send();
  }
}
</script>

<template>
  <div v-for="chat in recvList" :key="chat.chatId">
    <div>{{ chat.userId }} : {{ chat.content }}</div>
  </div>
  <div>
    <button @click="connection()">버튼</button>
  </div>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="mb-3">
        <label for="subject" class="form-label">채팅 내용 작성 </label>
        <input
          type="text"
          class="form-control"
          id="content"
          name="replyCotent"
          placeholder="입력하시오..."
          v-model="chat.content"
        />
      </div>

      <div class="d-flex justify-content-end">
        <input type="submit" id="btn-reply" class="btn btn-outline-primary mb-3" value="작성 " />
      </div>
    </form>
  </div>
</template>

<style scoped></style>
