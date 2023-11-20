import bigQuery from "@/api/bigQueryApi";
import { toast } from "react-hot-toast";
import CountryType from "@/types/CountryType";
import IndicatorType from "@/types/IndicatorType";
import QueryDataType from "@/types/QueryDataType";

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
const bigQueryServie = {
  fetchDataCountry,
  fetchDataIndicator,
  fetchRunQuery
};

export default bigQueryServie;