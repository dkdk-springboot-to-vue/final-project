import { localAxios } from '@/util/http-commons';

const local = localAxios();
const url = '/collection';

function listCollection(userId, success, fail) {
  local.get(`${url}/${userId}`).then(success).catch(fail);
}

function detailCollection(cId, success, fail) {
  local.get(`${url}/detail/${cId}`).then(success).catch(fail);
}

function deleteCollection(cId, success, fail) {
  local.delete(`${url}/${cId}`).then(success).catch(fail);
}

function updateCollection(dto, success, fail) {
  local.put(`${url}/bookmark`, dto).then(success).catch(fail);
}

function listBookmark(cId, success, fail) {
  local.get(`${url}?cid=${cId}`).then(success).catch(fail);
}

function registBookmark(dto, success, fail) {
  local.post(`${url}/bookmark`, dto).then(success).catch(fail);
}

function deleteBookmark(savedId, success, fail) {
  local.delete(`${url}/bookmark/${savedId}`).then(success).catch(fail);
}

export {
  listCollection,
  detailCollection,
  deleteCollection,
  updateCollection,
  listBookmark,
  registBookmark,
  deleteBookmark,
};
