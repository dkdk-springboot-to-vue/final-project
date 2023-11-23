import { localAxios } from "@/util/http-commons";

const local = localAxios();

const url = "/member";

function detailMember(userId, success, fail) {
  local.get(`${url}/${userId}`).then(success).catch(fail);
}

function registMember(user, success, fail) {
  local.post(`${url}`, JSON.stringify(user)).then(success).catch(fail);
}

function deleteMember(userId, success, fail) {
  local.delete(`${url}/${userId}`).then(success).catch(fail);
}

function modifyMember(user, success, fail) {
  local.put(`${url}`, JSON.stringify(user)).then(success).catch(fail);
}

export { detailMember, registMember, deleteMember, modifyMember };
