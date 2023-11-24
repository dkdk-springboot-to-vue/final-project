<script setup>
import { defineProps, ref } from 'vue';
defineProps({ room: Object });

const user = ref('');
user.value = sessionStorage.getItem('userId');
</script>

<template>
  <tr class="text-center">
    <th scope="row">
      <router-link :to="{ name: 'detail-chatroom', params: { roomid: room.roomId } }">
        {{ room.title }}
      </router-link>
    </th>
    <td>
      <template v-if="room.senderId == user">
        {{ room.receiverId }}
      </template>
      <template v-else>
        {{ room.senderId }}
      </template>
    </td>
    <td>{{ room.createdAt }}</td>
  </tr>
</template>

<style scoped></style>
