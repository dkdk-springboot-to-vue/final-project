<script setup>
import { ref, defineProps, onMounted, watch } from 'vue';
import { listCollection, registCollection, registBookmark } from '@/api/collection';

const props = defineProps({ selectAttraction: Object });

const colDto = ref({
  userId: 'ssafy', // 로그인되면 바꾸기
  title: '',
});

const bookmarkDto = ref({
  cid: 0,
  contentId: 0,
  userId: 'ssafy', // 로그인되면 바꾸기
  title: '',
});

const collections = ref([]);

const getCollectoinList = () => {
  console.log('getCollectionList');
  listCollection(
    'ssafy', // 로그인되면 바꾸기
    ({ data }) => {
      console.log(data);
      data.forEach((ele) => {
        collections.value.push(ele);
      });
    },
    (err) => {
      console.log(err);
    }
  );
};

const registCol = () => {
  registCollection(
    colDto.value,
    ({ data }) => {
      collections.value.push(data);
    },
    (err) => {
      console.log(err);
    }
  );
};

const registBM = (cid) => {
  bookmarkDto.value.cid = cid;
  bookmarkDto.value.contentId = props.selectAttraction.content_id;
  bookmarkDto.value.title = props.selectAttraction.title;
  console.log('Dto 받음 registBM');
  console.log(bookmarkDto.value);
  registBookmark(
    bookmarkDto.value,
    ({ data }) => {
      console.log(data);
    },
    (err) => {
      console.log(err);
      alert(err.response.data);
    }
  );
};

onMounted(() => {
  getCollectoinList();
});
</script>

<template>
  <div
    class="modal fade"
    id="collectionModal"
    tabindex="-1"
    aria-labelledby="collectionModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="collectionModalLabel">
            <!-- <template v-if="props.type == 1">좋아요 목록</template>
            <template v-else="props.type">싫어요 목록</template> -->
          </h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body" id="modal-scroll">
          <template v-for="collection in collections" :key="collection.cId">
            <div class="d-flex">
              <div>{{ collection.title }} : {{ collection.cid }}</div>
              <button class="btn btn-primary" @click="registBM(collection.cid)">선택</button>
            </div>
          </template>
          <!-- <AttractionLikeListItem
            v-for="mem in ratedMembers"
            :key="mem.userId"
            :mem="mem"
            :contentId="props.content_id"
            :show-modal="showModal"
          >
          </AttractionLikeListItem> -->

          <div class="d-flex">
            <input type="text" v-model="colDto.title" />
            <button class="btn btn-primary" @click="registCol()">새 컬렉션 생성</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
