import axios from "axios";

const baseURL = import.meta.env.MODE === 'development'
    ? 'http://localhost:8080'
    : '/api';
const token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX0xJQlJBUklBTiJdLCJzdWIiOiJsaWJyYXJpYW4iLCJpYXQiOjE3NTExMTU2NjIsImV4cCI6MTc1MTk3OTY2Mn0.K5Aq7EiwsC9VesIjlUtuCZNNCF-yU8X8yaGk-PWgcQM";

// const token = localStorage.getItem("token");
// const headers = {
//     "Content-Type": "application/json",
// };
//
// if (token) {
//     headers.Authorization = `Bearer ${token}`;
// }

const axiosInstance = axios.create({
    baseURL: "/api",
    headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
    },
    // headers
});

export default axiosInstance;