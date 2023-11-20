import axios from 'axios';

// local vue api axios instance
function localAxios() {
  const instance = axios.create({
    baseURL: `http://localhost/api`,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
    },
  });
  return instance;
}

export { localAxios };
