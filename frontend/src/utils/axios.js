import axios from 'axios';

const BASEURL = `http://${process.env.REACT_APP_IP}/api/v1`;

const axiosInstance = axios.create({
  baseURL: BASEURL,
});

axiosInstance.interceptors.request.use(
  config => {
    return config;
  },
  error => {
    console.log('axiosInstance request Error');
    return Promise.reject(error);
  },
);

axiosInstance.interceptors.response.use(
  response => response,
  async error => {
    return Promise.reject(error);
  },
);

export default axiosInstance;
