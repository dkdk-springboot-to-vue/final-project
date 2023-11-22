<script setup>
import { ref, watch, onMounted, defineProps } from 'vue';

var map;
const positions = ref([]);
const markers = ref([]);

const props = defineProps({ attractions: Array, selectAttraction: Object });

watch(
  () => props.selectAttraction.value,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(
      props.selectAttraction.latitude,
      props.selectAttraction.longitude
    );

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);

    const attraction = props.selectAttraction;
    const content = generateOverlayContent(attraction);
    displayOverlay(moveLatLon, content);
  },
  { deep: true }
);

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script');
    // autoload=false 파라미터 추가해주기
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=384f5ff7c10a90767f8f86d57f6fdac8&libraries=services,clusterer`;
    // 이 아래 주석 지우면 안됨
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

watch(
  () => props.attractions.value,
  () => {
    console.log('prpos : ' + props.attractions.value);
    positions.value = [];
    props.attractions.forEach((attraction) => {
      let obj = {};
      obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
      obj.title = attraction.title;

      positions.value.push(obj);
    });
    loadMarkers();
  },
  { deep: true }
);

const initMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);

  // loadMarkers();
};

const loadMarkers = () => {
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  deleteMarkers();

  // 마커 이미지를 생성합니다
  //   const imgSrc = require("@/assets/map/markerStar.png");
  // 마커 이미지의 이미지 크기 입니다
  //   const imgSize = new kakao.maps.Size(24, 35);
  //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

  // 마커를 생성합니다
  markers.value = [];
  positions.value.forEach((position, index) => {
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      // image: markerImage, // 마커의 이미지
    });

    markers.value.push(marker);

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function () {
      // 마커 위에 인포윈도우를 표시합니다
      //   infowindow.open(map, marker);
      closeOverlay();
      const content = generateOverlayContent(props.attractions[index]);
      displayOverlay(marker.getPosition(), content);
      console.log('마커클릭');
    });
  });

  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  );

  map.setBounds(bounds);
};

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
};

// 오버레이
const generateOverlayContent = (attraction) => {
  const wrap = document.createElement('div');
  wrap.classList.add('wrap');

  const info = document.createElement('div');
  info.classList.add('info');

  const title = document.createElement('div');
  title.classList.add('title');
  title.textContent = attraction.title;

  const close = document.createElement('div');
  close.classList.add('close');
  close.setAttribute('title', '닫기');
  close.addEventListener('click', closeOverlay); // closeOverlay 함수를 클릭 이벤트에 연결

  const body = document.createElement('div');
  body.classList.add('body');

  const img = document.createElement('div');
  img.classList.add('img');

  const image = document.createElement('img');
  image.setAttribute('src', attraction.first_image);
  image.setAttribute('width', '73');
  image.setAttribute('height', '70');

  const desc = document.createElement('div');
  desc.classList.add('desc');

  const address = document.createElement('div');
  address.classList.add('ellipsis');
  address.textContent = attraction.addr1;

  const postalCode = document.createElement('div');
  postalCode.classList.add('jibun', 'ellipsis');
  postalCode.textContent = attraction.postalCode;

  desc.appendChild(address);
  desc.appendChild(postalCode);

  img.appendChild(image);

  title.appendChild(close);

  body.appendChild(img);
  body.appendChild(desc);

  info.appendChild(title);
  info.appendChild(body);

  wrap.appendChild(info);

  return wrap;
};

let overlay = null; // 초기값을 null로 설정하여 초기화

const displayOverlay = (position, content) => {
  // 기존 overlay가 있다면 제거
  if (overlay) {
    overlay.setMap(null);
  }

  overlay = new kakao.maps.CustomOverlay({
    content: content,
    map: map,
    position: position,
  });
};

const closeOverlay = () => {
  if (overlay) {
    overlay.setMap(null);
  }
};
</script>

<template>
  <div id="map"></div>
</template>

<style>
#map {
  /* width: 100%; */
  height: 100%;
}
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: '';
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png');
}
.info .link {
  color: #5085bb;
}
</style>
