<script setup>
import { ref, onMounted, watch } from 'vue';
import { listSido, listGugun, listAttr, getAttractionListByDist } from '@/api/map';

import AttractionListItem from '@/components/attraction/item/AttractionListItem.vue';
import AttractionDetail from '@/components/attraction/AttractionDetail.vue';

import VKakaoMap from '@/components/common/VKakaoMap.vue';
import VSelect from '@/components/common/VSelect.vue';

const sidoList = ref([]);
const gugunList = ref([{ text: '구군선택', value: '' }]);
const attractions = ref([]);
const selectAttraction = ref({});

let attractionArr = [];
let attractionByDistArr = [];

const attr = ref({
  sido_code: 0,
  gugun_code: 0,
  sido_name: '',
  gugun_name: '',
  latitude: 0.0,
  longitude: 0.0,
  dist: 2,
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
      attractionArr = data;
      attractions.value = attractionArr;
    },
    (err) => {
      console.log(err);
    }
  );
};

const getAttractionsByDist = (flag) => {
  if (flag) {
    selectAttraction.value.dist = attr.value.dist;
    getAttractionListByDist(
      attr.value,
      ({ data }) => {
        attractionByDistArr = data;
        attractions.value = attractionByDistArr;
      },
      (err) => {
        console.log(err);
      }
    );
  } else {
    if (attractionArr.length > 0) {
      attractions.value = attractionArr;
    }
  }
};

const viewAttr = (selectedAttr) => {
  console.log('selected');
  console.log(selectedAttr);
  selectAttraction.value = selectedAttr;
  selectAttraction.value.dist = attr.value.dist;
  attr.value.latitude = selectedAttr.latitude;
  attr.value.longitude = selectedAttr.longitude;

  getAttractionsByDist(checkbox.value);
};

const checkbox = ref(false);

const changeDist = () => {
  getAttractionsByDist(checkbox.value);
};

const toggleCheckbox = () => {
  checkbox.value = !checkbox.value;
};

watch(
  () => checkbox.value,
  () => {
    getAttractionsByDist(checkbox.value);
  }
);
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

      <div class="col d-flex">
        <div class="panel panel-default d-flex">
          <div class="panel-heading">
            <p class="panel-title">주위 반경 보기</p>
          </div>
          <div class="panel-body">
            <!--Only code you need is this label-->
            <label class="switch">
              <input type="checkbox" @click="toggleCheckbox" />
              <div class="slider round"></div>
            </label>
            <p>{{ checkbox }}</p>
          </div>
        </div>
        <div class="d-flex">
          거리조절
          <input
            type="range"
            id="a"
            name="ages"
            min="0.5"
            max="5"
            step="0.5"
            v-model="attr.dist"
            @change="changeDist()"
          />
          <div>{{ attr.dist }}</div>
        </div>
      </div>
    </div>
    <div class="row" id="map-attraction">
      <div class="col-2" id="attraction-scroll">
        <AttractionDetail
          v-if="Object.keys(selectAttraction).length > 0"
          :selectAttraction="selectAttraction"
        />
      </div>
      <div class="col-8">
        <VKakaoMap
          :attractions="attractions"
          :selectAttraction="selectAttraction"
          :checkbox="checkbox"
          :dist="attr.dist"
        />
      </div>
      <div class="col-2" id="attraction-scroll">
        <table class="table table-hover">
          <thead>
            <tr class="text-center">
              <th scope="col">장소명</th>
              <th scope="col">주소</th>
              <th scope="col"></th>
              <th scope="col">위치</th>
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

#map-attraction {
  height: 700px;
}

#attraction-scroll {
  height: 100%;
  overflow: auto;
}

.switch {
  position: relative;
  display: inline-block;
  width: 30px;
  height: 17px;
}

.switch input {
  display: none;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

.slider:before {
  position: absolute;
  content: '';
  height: 13px;
  width: 13px;
  left: 2px;
  bottom: 2px;
  background-color: white;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

input:checked + .slider {
  background-color: #101010;
}

input:focus + .slider {
  box-shadow: 0 0 1px #101010;
}

input:checked + .slider:before {
  -webkit-transform: translateX(13px);
  -ms-transform: translateX(13px);
  transform: translateX(13px);
}

.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
</style>
