import axios from "axios";

export const API_BASE_URL = "http://localhost:1803";

const jwtToken = localStorage.getItem("jwt")

export const api = axios.create({baseURL:API_BASE_URL,
headers:{
  "Authorization" : `Bearer ${jwtToken}`,
  "Content-type" : "application/json"
}})