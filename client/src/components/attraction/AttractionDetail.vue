<script setup>
import { ref, defineProps, watch } from 'vue';
import { toggleLike, detailAttr } from '@/api/map';

import AttractionLikeList from '@/components/attraction/AttractionLikeList.vue';
const props = defineProps({ selectAttraction: Object });

const likeDto = ref({
  contentId: 0,
  userId: 'ssafy',
  type: 0,
});

const likeInfo = ref({
  likeCount: 0,
  dislikeCount: 0,
});

const state = ref(0);

const openModal = (type) => {
  console.log('openModal ::: ' + type);
  state.value = type;
};

const fetchAttractionDetails = () => {
  detailAttr(
    props.selectAttraction.content_id,
    ({ data }) => {
      likeInfo.value.likeCount = data.likeCount;
      likeInfo.value.dislikeCount = data.dislikeCount;
    },
    (error) => {
      console.log(error);
    }
  );
};

// selectAttraction이 변경될 때마다 좋아요 및 싫어요 카운트를 최신화
watch(
  () => props.selectAttraction,
  () => {
    fetchAttractionDetails();
  }
);

const registEmote = (val) => {
  likeDto.value.type = val;
  likeDto.value.contentId = props.selectAttraction.content_id;

  toggleLike(
    likeDto.value,
    ({ data }) => {
      // 좋아요/싫어요 토글 성공 시 관광지 정보 다시 가져오기
      fetchAttractionDetails();
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div>
    {{ selectAttraction }}
  </div>

  <button @click="registEmote(1)">좋아요 : {{ likeInfo.likeCount }}</button>
  <button @click="registEmote(2)">싫어요 : {{ likeInfo.dislikeCount }}</button>

  <button
    type="button"
    class="btn btn-primary"
    data-bs-toggle="modal"
    data-bs-target="#emoteModal"
    @click="openModal(1)"
  >
    좋아요리스트
  </button>
  <button
    type="button"
    class="btn btn-primary"
    data-bs-toggle="modal"
    data-bs-target="#emoteModal"
    @click="openModal(2)"
  >
    싫어요리스트
  </button>

  <AttractionLikeList :content_id="selectAttraction.content_id" :type="state" />
</template>

<style scoped></style>
