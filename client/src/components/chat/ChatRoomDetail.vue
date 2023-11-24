<script setup>
import Stomp from 'webstomp-client';
import SockJs from 'sockjs-client';
// import * as StompJs from '@stomp/stompjs';

import { ref, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import { detailRoom } from '@/api/chat';

const route = useRoute();
const router = useRouter();

const chatContainer = ref(null);

const user = ref('');
user.value = sessionStorage.getItem('userId');

const { roomid } = route.params;
console.log('roomId : ' + roomid);
const chatroom = ref({});

const chatErrMsg = ref('');

const chat = ref({
  roomId: roomid,
  chatId: 0,
  createdAt: '',
  content: '',
  userId: sessionStorage.getItem('userId'),
});

onMounted(() => {
  getChatRoom();
  connection(); // 컴포넌트가 마운트되면 소켓 연결 시도
  console.log();
  scrollToBottom();
  watch(recvList, () => {});
});

const getChatRoom = () => {
  console.log('get detail chatroom');
  detailRoom(roomid, ({ data }) => {
    console.log(data);
    chatroom.value = data;
    recvList.value = data.chatList;
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
  const serverURL = `${process.env.VUE_APP_API_URL}/ws`;
  let socket = new SockJs(serverURL);
  stompClient = Stomp.over(socket);
  console.log(stompClient);
  console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
  stompClient.connect(
    {},
    (frame) => {
      connected = true;
      console.log('소켓 연결 성공', frame);
      stompClient.subscribe(`/sub/chat/${roomid}`, (res) => {
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
    chat.value.content = '';
  }
}

function scrollToBottom() {
  if (chatContainer.value) {
    chatContainer.value.scrollTop = chatContainer.value.scrollHeight;
  }
}
</script>

<template>
  <div class="container">
    <div id="chat-wrapper" ref="chatContainer">
      <div>
        <strong>{{ chatroom.title }}</strong> (으)로부터 시작된 메시지
      </div>
      <div
        v-for="chat in recvList"
        :key="chat.chatId"
        :class="{ 'align-right': chat.userId === user, 'align-left': chat.userId !== user }"
      >
        <div>{{ chat.userId }} : {{ chat.content }}</div>
      </div>
    </div>
    <div>
      <form @submit.prevent="onSubmit">
        <div class="d-flex justify-content-end">
          <div class="input-group mb-3">
            <input
              type="text"
              class="form-control"
              name="replyCotent"
              placeholder="채팅 내용 작성..."
              aria-label="Recipient's username"
              aria-describedby="button-addon2"
              v-model="chat.content"
            />
            <button class="btn btn-outline-secondary" type="submit" id="button-addon2">
              Button
            </button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
#chat-wrapper {
  height: 200px;
  overflow: auto; /* 또는 overflow: hidden; */
  /* display: flex; */
  flex-direction: column_reverse;
}

.align-right {
  text-align: right;
}
</style>
