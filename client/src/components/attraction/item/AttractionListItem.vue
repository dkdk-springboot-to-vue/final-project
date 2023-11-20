<script setup>
import { defineProps } from 'vue';
defineProps({ attraction: Object });
</script>

<template>
  <tr>
    <!-- <td><img :src="attr.first_image" alt="" /></td> -->
    <th>{{ attraction.title }}</th>
    <td>{{ attraction.addr1 }}</td>
    <td>{{ attraction.addr }}</td>
    <td>{{ attraction.lat }}</td>
    <td>{{ attraction.lng }}</td>
  </tr>
</template>

<style scoped></style>
