"use client"
import { useEffect, useState } from "react"
import CountryType from "@/types/CountryType"
import React from "react"
import SelectCountry from "./SelectCountry"
import SelectYear from "./SelectYear"
import SelectIndicator from "./SelectIndicator"
import Slider from "./SlideValue"
import IndicatorType from "@/types/IndicatorType"
import { Button } from "@nextui-org/react"
import QueryDataType from "@/types/QueryDataType"
import GraphType from "@/types/GraphType"
import bigQueryService from "@/service/bigQueryService"
import { toast } from "react-hot-toast"
import ChartComponent from "../charts/chart"
import ModalSaveQuery from "../saveQueryComponents/SaveQueryComponent"

interface MainDashboardProps {
  countries: CountryType[]
  indicators: IndicatorType[]
}

const MainDashboard = ({ countries, indicators }: MainDashboardProps) => {
  const dataForm: QueryDataType = {
    countryCode: "",
    seriesCode: "",
    year: "",
    value: "",
  }

  const [isEnable, setIsEnable] = useState<boolean>(false)
  const [queryFormData, setQueryData] = useState<QueryDataType>(dataForm)
  const [isQueryLoad, setIsQuery] = useState<boolean>(false)
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false)
  const [dataGraph, setDataGraph] = useState<GraphType>()

  useEffect(() => {
    const areAllFieldsComplete =
      !!queryFormData.countryCode &&
      !!queryFormData.seriesCode &&
      !!queryFormData.year

    setIsEnable(areAllFieldsComplete)
  }, [queryFormData])

  const handleProperty = (property: string, value: string) => {
    setQueryData((prevQueryData) => {
      let updatedQueryData = { ...prevQueryData, [property]: value }
      console.log(updatedQueryData)
      return updatedQueryData
    })
  }
  const executeQuery = async () => {
    setIsQuery(true)
    try {
      const data = await bigQueryService.fetchRunQuery(queryFormData)

      const values: string[] = data.map((item: { value: any }) => item.value)
      const years: string[] = data.map((item: { year: any }) => item.year)
      const indicator: string = data[0].indicator_name
      const country: string = data[0].country_name
      const query: string = await bigQueryService.getQuery(queryFormData)

      const dataChart: GraphType = {
        values,
        years,
        indicator,
        country,
        query,
      }
      console.log(dataChart)
      setDataGraph(dataChart)
    } catch (error) {
      toast.error("Error al cargar datos")
    } finally {
      setIsQuery(false)
    }
  }
  return (
    <>
      <div className=" w-[800px] ">
        <div className="text-center text-xl mt-4">
          Below you can choose the filters you want to see the expenses
        </div>
        <div className="flex flex-row justify-around ">
          <div className="flex flex-col ">
            <SelectCountry
              countries={countries}
              handleProperty={handleProperty}
            />
            <SelectYear handleProperty={handleProperty} />
            <Slider handleProperty={handleProperty} />
          </div>
          <div className="flex flex-col w-[400px] ">
            <SelectIndicator
              indicators={indicators}
              handleProperty={handleProperty}
            />
            <div className="w-[400px] pt-6 flex flex-row justify-center">
              <div className="mr-3">
                <Button
                  color="primary"
                  isDisabled={!isEnable}
                  onClick={executeQuery}
                >
                  Let&apos;s query!!
                </Button>
              </div>
              
            </div>
            <div>
              {isQueryLoad ? (
                <div className="flex justify-center items-center pt-5">
                  <div className="animate-spin rounded-full h-32 w-32 border-b-2 border-gray-900 "></div>
                </div>
              ) : (
                dataGraph && (
                  <div className="pt-4">
                    <ChartComponent queryData={dataGraph} />
                    <div className="flex flex-row justify-center mb-10 w-[100%]">
                      <ModalSaveQuery queryData={dataGraph} />
                    </div>
                  </div>
                )
              )}
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default MainDashboard
