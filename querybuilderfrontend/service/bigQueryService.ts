import bigQuery from "@/api/bigQueryApi";
import { toast } from "react-hot-toast";
import CountryType from "@/types/CountryType";
import IndicatorType from "@/types/IndicatorType";
import QueryDataType from "@/types/QueryDataType";
import QuerySaveFormType from "@/types/QuerySaveFormType";
import CommentFormType from "@/types/CommentFormType";
import QueryToShow from "@/types/QueryToShow";
import GraphType from "@/types/GraphType";

const fetchDataCountry = async () => {
  try {
    const response = await bigQuery.getCountries();
    const countries: CountryType[] = response.data;
    return countries;
  } catch (error) {
    toast.error("Error al cargar los países");
  }
};

const fetchDataIndicator = async () => {
  try {
    const response = await bigQuery.getIndicators();
    const indicators: IndicatorType[] = response.data;
    return indicators;
  } catch (error) {
    toast.error("Error al cargar los indicadores");
  }
}

const fetchRunQuery = async(queryData:QueryDataType) => {
  try {
    const response = await bigQuery.runQuery(queryData);
    const data = response.data;
    return data;
  } catch (error) {
    toast.error("Error al cargar los indicadores");
  }
}

const getQuery = async(queryData:QueryDataType) => {
  try{
    const response = await bigQuery.getQuery(queryData);
    const data = response.data;
    return data;
  }catch(error){
    toast.error("Error al cargar la query");
  }
}

const fetchSaveQuery = async(queryData:QuerySaveFormType) => {
  try{
    const response = await bigQuery.saveQuery(queryData);
    const data = response.data;
    return data;
  }catch(error){
    toast.error("Error al guardar la query");
  }
}

const fetchGetCommentByQuery = async(queryId: string) => {
  try{
    const response = await bigQuery.getCommentByQuery(queryId);
    const data = response.data;
    return data;
  }catch(error){
    toast.error("Error al cargar los comentarios");
  }
}

const fetchCreateComment = async(comment: CommentFormType) => {
  try{
    const response = await bigQuery.createComment(comment);
    const data = response.data;
    return data;
  }catch(error){
    toast.error("Error al crear el comentario");
  }

}

const executeSelectedQuery = async(queryData:QueryToShow) => {
  try{
    const response = await bigQuery.executeExistingQuery(queryData);
    const data = response.data;

      const values: string[] = data.map((item: { value: any }) => item.value)
      const years: string[] = data.map((item: { year: any }) => item.year)
      const indicator: string = data[0].indicator_name
      const country: string = data[0].country_name
      const query: string = queryData.query

      const dataChart: GraphType = {
        values,
        years,
        indicator,
        country,
        query,
      }
    return dataChart;
  }catch(error){
    toast.error("Error al ejecutar la query");
  }

}

const fetchAllQueries = async() => {
  try{
    const response = await bigQuery.getAllQueries();
    const data = response.data;
    return data;
  }catch(error){
    toast.error("Error al cargar las queries");
  }
}

const bigQueryService = {
  fetchDataCountry,
  fetchDataIndicator,
  fetchRunQuery,
  getQuery,
  fetchSaveQuery,
  fetchGetCommentByQuery,
  fetchCreateComment,
  executeSelectedQuery,
  fetchAllQueries,
};

export default bigQueryService;