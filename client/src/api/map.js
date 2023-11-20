import { localAxios } from '@/util/http-commons';

const local = localAxios();
const url = '/map';

function listSido(success, fail) {
  local.get(`${url}`).then(success).catch(fail);
}

function listGugun(sido_code, success, fail) {
  local.get(`${url}/gugun/${sido_code}`).then(success).catch(fail);
}

function listAttr(attr, success, fail) {
  local.post(`${url}/mapview`, attr).then(success).catch(fail);
}

export { listSido, listGugun, listAttr };
