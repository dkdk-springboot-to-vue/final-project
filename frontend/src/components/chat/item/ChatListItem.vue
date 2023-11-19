<script setup>
defineProps({ room: Object });
</script>

<template>
  <tr class="text-center">
    <th scope="row">
      <router-link
        :to="{ name: 'detail-chatroom', params: { roomid: room.roomId } }"
      >
        {{ room.roomId }}
      </router-link>
    </th>
    <td></td>
    <td>{{ room.createdAt }}</td>
  </tr>
</template>

<style scoped></style>
