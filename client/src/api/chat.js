import { localAxios } from '@/util/http-commons';

const local = localAxios();
const url = '/chat';

function listRoom(userId, success, fail) {
  local.get(`${url}/${userId}`).then(success).catch(fail);
}

function detailRoom(roomId, success, fail) {
  local.get(`${url}/room/${roomId}`).then(success).catch(fail);
}

function registRoom(dto, success, fail) {
  local.post(`${url}/room`, dto).then(success).catch(fail);
}

export { listRoom, detailRoom, registRoom };
