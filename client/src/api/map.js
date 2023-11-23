import { localAxios } from '@/util/http-commons';

const local = localAxios();
const url = '/map';

function listSido(success, fail) {
  local.get(`${url}`).then(success).catch(fail);
}

function listGugun(sido_code, success, fail) {
  local.get(`${url}/gugun/${sido_code}`).then(success).catch(fail);
}

function listAttr(attrDto, success, fail) {
  local.post(`${url}/mapview`, attrDto).then(success).catch(fail);
}

function getAttractionListByDist(attrDto, success, fail) {
  local.post(`${url}/mapview/dist`, attrDto).then(success).catch(fail);
}

function detailAttr(content_id, success, fail) {
  local.get(`${url}/attr/${content_id}`).then(success).catch(fail);
}

function toggleLike(likeDto, success, fail) {
  local.post(`${url}/like`, likeDto).then(success).catch(fail);
}

function listAttrLikeMember(content_id, success, fail) {
  local.get(`${url}/attr/like/${content_id}`).then(success).catch(fail);
}

export {
  listSido,
  listGugun,
  listAttr,
  detailAttr,
  toggleLike,
  listAttrLikeMember,
  getAttractionListByDist,
};
