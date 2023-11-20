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
import { toast } from "react-hot-toast";

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
      setDataGraph(data)
      console.log(data)
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
            <div className="w-[400px] pt-6">
              <Button color="primary" isDisabled={!isEnable} onClick={executeQuery}>
                Let&apos;s query!!
              </Button>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default MainDashboard
