import axios from 'axios';

// local vue api axios instance
function localAxios() {
  const instance = axios.create({
    baseURL: `${process.env.VUE_APP_API_URL}/api`,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
    },
  });
  return instance;
}

export { localAxios };
