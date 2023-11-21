import CommentFormType from "@/types/CommentFormType"
import QueryDataType from "@/types/QueryDataType"
import QuerySaveFormType from "@/types/QuerySaveFormType"
import QueryToShow from "@/types/QueryToShow"
import axios from "axios"

const URL_API = "http://localhost:8080/api/bigquery"
const URL_API_USER = "http://localhost:8080/api/userQuery"
const URL_API_COMMENT = "http://localhost:8080/api/comment"

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

const getCommentByQuery = (queryId: string) => {
  return axios.get(`${URL_API_COMMENT}/${queryId}`)
}

const createComment = (data: CommentFormType) => {
  return axios.post(`${URL_API_COMMENT}/`, data)
}

const executeExistingQuery = (query: QueryToShow) => {
  return axios.post(`${URL_API}/internationalEducation/query`, query)
}

const getAllQueries = () => {
  return axios.get(`${URL_API_USER}/`)
}
const bigQuery = { getCountries, getIndicators, runQuery, getQuery, saveQuery, getCommentByQuery, createComment, executeExistingQuery, getAllQueries }

export default bigQuery
