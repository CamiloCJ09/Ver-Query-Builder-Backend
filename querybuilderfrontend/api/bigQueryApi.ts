import QueryDataType from "@/types/QueryDataType"
import QuerySaveFormType from "@/types/QuerySaveFormType"
import axios from "axios"

const URL_API = "http://localhost:8080/api/bigquery"
const URL_API_USER = "http://localhost:8080/api/userQuery"

const getCountries = () => {
  return axios.get(`${URL_API}/countries`)
}

const getIndicators = () => {
  return axios.get(`${URL_API}/indicators`)
}

const runQuery = (data: QueryDataType) => {
  return axios.post(`${URL_API}/internationalEducation`, data)
}

const getQuery = (data: QueryDataType) => {
  return axios.post(`${URL_API}/internationalEducation/get`, data)
}

const saveQuery = (data: QuerySaveFormType) => {
  return axios.post(`${URL_API_USER}/`, data)
}
const bigQuery = { getCountries, getIndicators, runQuery, getQuery, saveQuery }

export default bigQuery
