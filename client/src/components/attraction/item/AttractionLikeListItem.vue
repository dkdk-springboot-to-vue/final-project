<script setup>
import { defineProps, ref, defineEmits } from 'vue';
import { useRouter } from 'vue-router';
import { registRoom } from '@/api/chat';
const props = defineProps({ mem: Object, contentId: Number });
const emit = defineEmits(['close-modal']);
const chatDto = ref({
  contentId: 0,
  senderId: 'ssafy',
  receiverId: '',
});

const router = useRouter();

const startChat = () => {
  chatDto.value.contentId = props.contentId;
  chatDto.value.receiverId = props.mem.likeUserId;
  console.log(chatDto.value);
  registRoom(
    chatDto.value,
    ({ data }) => {
      console.log(data.roomId);
      // emit('close-modal');
      router.push({ name: 'detail-chatroom', params: { roomid: data.roomId } }).then(() => {
        window.location.reload();
      });
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div id="like-wrap" class="d-flex justify-content-between mb-1">
    <div>
      {{ mem.likeUserId }}
    </div>

    <button class="btn btn-primary" @click="startChat()">채팅</button>
  </div>
</template>

<style scoped></style>
