import { localAxios } from '@/util/http-commons';

const local = localAxios();
const url = '/chat';

function listRoom(success, fail) {
  local.get(`${url}/room`).then(success).catch(fail);
}

function detailRoom(roomId, success, fail) {
  local.get(`${url}/room/${roomId}`).then(success).catch(fail);
}

export { listRoom, detailRoom };
