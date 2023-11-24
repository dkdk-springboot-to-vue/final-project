<script setup>
import { ref, onMounted } from 'vue';
// import { useRouter } from 'vue-router';
import { listRoom } from '@/api/chat';

import ChatListItem from '@/components/chat/item/ChatListItem.vue';

// const router = useRouter();

const chatrooms = ref([]);
onMounted(() => {
  getChatRoomList();
});

const getChatRoomList = () => {
  console.log('채팅 목록 얻어오기');
  listRoom(
    sessionStorage.getItem('userId'),
    ({ data }) => {
      console.log(data);
      chatrooms.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div class="row justify-content-center">
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-3 shadow-sm bg-light text-center">
        <mark class="sky">채팅목록</mark>
      </h2>
    </div>

    <div class="col-lg-8 col-md-10 col-sm-12">
      <table class="table table-hover">
        <thead>
          <tr class="text-center">
            <th scope="col">관광지명</th>
            <th scope="col">채팅상대</th>
            <th scope="col">생성일자</th>
          </tr>
        </thead>
        <tbody>
          <ChatListItem v-for="room in chatrooms" :key="room.roomId" :room="room"> </ChatListItem>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped></style>
