import bigQuery from "@/api/bigQueryApi";
import { toast } from "react-hot-toast";
import CountryType from "@/types/CountryType";
import IndicatorType from "@/types/IndicatorType";
import QueryDataType from "@/types/QueryDataType";
import QuerySaveFormType from "@/types/QuerySaveFormType";

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
const bigQueryServie = {
  fetchDataCountry,
  fetchDataIndicator,
  fetchRunQuery,
  getQuery,
  fetchSaveQuery
};

export default bigQueryServie;