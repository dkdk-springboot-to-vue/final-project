<script setup>
import { ref, defineProps, onMounted, watch } from 'vue';
import { listAttrLikeMember } from '@/api/map';

import AttractionLikeListItem from '@/components/attraction/item/AttractionLikeListItem.vue';

const props = defineProps({ type: Number, content_id: Number });

const ratedMembers = ref([]);

onMounted(() => {
  getAttrLikeMemberList();
});

const getAttrLikeMemberList = () => {
  console.log('========getattrmemlist====');
  listAttrLikeMember(
    props.content_id,
    ({ data }) => {
      let ratedArr = [];
      data.forEach((ele) => {
        console.log(ele.type);
        console.log('props : ' + props.type);
        if (ele.type == props.type) ratedArr.push(ele);
      });
      ratedMembers.value = ratedArr;
      console.log(ratedMembers.value);
    },
    (err) => {
      console.log(err);
    }
  );
};

onMounted(() => {
  getAttrLikeMemberList(); // 초기 마운트 시 데이터 가져오기
});

watch(
  () => props.type, // content_id의 변화를 감지
  () => {
    getAttrLikeMemberList(); // content_id가 변경될 때마다 데이터를 다시 가져오기
  }
);

watch(
  () => props.content_id, // content_id의 변화를 감지
  () => {
    getAttrLikeMemberList(); // content_id가 변경될 때마다 데이터를 다시 가져오기
  }
);
</script>

<template>
  <div
    class="modal fade"
    id="emoteModal"
    tabindex="-1"
    aria-labelledby="emoteModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="emoteModalLabel">
            <template v-if="props.type"> </template>
          </h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body" id="modal-scroll">
          <div v-for="mem in ratedMembers" :key="mem.userId">
            {{ mem.likeUserId }}
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#modal-scroll {
  height: 400px;
  overflow: auto;
}
</style>
