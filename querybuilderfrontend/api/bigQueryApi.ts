import QueryDataType from "@/types/QueryDataType"
import axios from "axios"

const URL_API = "http://localhost:8080/api/bigquery"

const getCountries = () => {
  return axios.get(`${URL_API}/countries`)
}

const getIndicators = () => {
  return axios.get(`${URL_API}/indicators`)
}

const runQuery = (data: QueryDataType) => {
  return axios.post(`${URL_API}/internationalEducation`, data)
}
const bigQuery = { getCountries, getIndicators, runQuery }

export default bigQuery
