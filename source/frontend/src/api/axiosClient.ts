import axios from 'axios';

const axiosClient = axios.create({
  baseURL: '/api/v1',
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor for Request
axiosClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token && config.headers) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Interceptor for Response
axiosClient.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    // Handle global errors if needed (e.g. 401 Unauthorized logout)
    if (error.response && error.response.status === 401) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      // Dispatch logout or redirect to login could be handled at component level or via window.location
    }
    return Promise.reject(error.response?.data || error.message);
  }
);

export default axiosClient;
