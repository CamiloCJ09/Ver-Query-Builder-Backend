"use client"

import React, { useEffect, useState } from "react"

import bigQueryService from "@/service/bigQueryService"

import { toast } from "react-hot-toast"
import { CircularProgress } from "@nextui-org/progress"
import CountryType from "@/types/CountryType"
import MainDashboard from "@/components/mainComponents/MainPage"
import IndicatorType from "@/types/IndicatorType"

const DashboardPage = () => {
  const [countries, setCountries] = useState<CountryType[]>([])
  const [indicators, setIndicators] = useState<IndicatorType[]>([])

  const fetchData = async () => {
    try {
      const [dataCountry, dataIndicators] = await Promise.all([
        bigQueryService.fetchDataCountry(),
        bigQueryService.fetchDataIndicator(),
      ])

      if (dataCountry) setCountries(dataCountry)
      if (dataIndicators) setIndicators(dataIndicators)
    } catch (error) {
      toast.error("Error al cargar datos")
    }
  }

  useEffect(() => {
    fetchData()
  }, [])

  if (!countries.length)
    return (
      <>
        <div className="flex items-center justify-center h-[100%]">
          <CircularProgress label="Loading" size="lg" />
        </div>
      </>
    )

  return (
    <>
      <div className="text-center text-xl mt-4 align-middle">
        <MainDashboard countries={countries} indicators={indicators} />
      </div>
    </>
  )
}

export default DashboardPage
