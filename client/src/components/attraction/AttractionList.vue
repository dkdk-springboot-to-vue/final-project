<script setup>
import { ref, onMounted } from 'vue';
import { listSido, listGugun, listAttr } from '@/api/map';

import AttractionListItem from '@/components/attraction/item/AttractionListItem.vue';

import VKakaoMap from '@/components/common/VKakaoMap.vue';
import VSelect from '@/components/common/VSelect.vue';

const sidoList = ref([]);
const gugunList = ref([{ text: '구군선택', value: '' }]);
const attractions = ref([]);
const selectAttraction = ref({});

const attr = ref({
  sido_code: 0,
  gugun_code: 0,
  sido_name: '',
  gugun_name: '',
});

onMounted(() => {
  getSidoList();
});

const getSidoList = () => {
  listSido(
    ({ data }) => {
      console.log(data);
      let options = [];
      options.push({ text: '시도선택', value: '' });
      data.forEach((sido) => {
        options.push({ text: sido.sido_name, value: sido.sido_code });
      });
      sidoList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};

const onChangeSido = (val) => {
  listGugun(
    val,
    ({ data }) => {
      attr.value.sido_code = val;
      let options = [];
      options.push({ text: '구군선택', value: '' });
      data.forEach((gugun) => {
        options.push({ text: gugun.gugun_name, value: gugun.gugun_code });
      });
      gugunList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};

const onChangeGugun = (val) => {
  attr.value.gugun_code = val;
  console.log(attr.value);
  getAttractions();
};

const getAttractions = () => {
  console.log(attr.value);
  listAttr(
    attr.value,
    ({ data }) => {
      attractions.value = data;
    },
    (err) => {
      console.log(err);
    }
  );
};

const viewAttr = (attr) => {
  selectAttraction.value = attr;
};
</script>

<template>
  <div class="text-center mt-3">
    <div class="alert alert-success" role="alert">관광지 목록</div>
    <div class="row mb-2">
      <div class="col d-flex flex-row-reverse">
        <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" />
      </div>
      <div class="col">
        <VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" />
      </div>
    </div>
    <div class="row">
      <div class="col-10">
        <VKakaoMap :attractions="attractions" :selectAttraction="selectAttraction" />
      </div>
      <div class="col-2" id="attraction-scroll">
        <table class="table table-hover">
          <thead>
            <tr class="text-center">
              <th scope="col">장소명</th>
              <th scope="col">주소</th>
              <th scope="col"></th>
              <th scope="col">위치</th>
              <th scope="col">위도</th>
              <th scope="col">경도</th>
            </tr>
          </thead>
          <tbody>
            <AttractionListItem
              v-for="attraction in attractions"
              :key="attraction.content_id"
              :attraction="attraction"
              @click="viewAttr(attraction)"
            ></AttractionListItem>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
mark.purple {
  background: linear-gradient(to top, #c354ff 20%, transparent 30%);
}

#attraction-scroll {
  height: 900px;
  overflow: auto;
}
</style>
