import React, { use, useEffect, useState } from "react"
import IndicatorType from "@/types/IndicatorType"
import { Select, SelectItem } from "@nextui-org/react"

interface SelectIndicatorProps {
  indicators: IndicatorType[]
  indicatorParam: string
  handleProperty: (property: string, value: string) => void
}

const SelectWorld = ({ indicators, handleProperty, indicatorParam }: SelectIndicatorProps) => {
  const [selectedTypeFilter, setSelectedTypeFilter] = useState<string>("indicator")

  const [selectedIndicator, setSelectedIndicator] = useState<string>(indicatorParam)

  const handleIndicator = (property: string, value: string) => {
    setSelectedIndicator(value)
    handleProperty(property, value)
  }
  useEffect(() => {
    handleProperty("seriesCode", indicatorParam)
  }, [])
  return (
    <>
      <div>
        <div className="text-center mt-3 font-bold text-lg">
          Choose the indicator to filter
        </div>

        {selectedTypeFilter === "indicator" && (
          <div className="flex w-full flex-row justify-center md:flex-nowrap gap-4 mt-5">
            <Select
              label="Select an indicator"
              placeholder="Search an indicator"
              className="w-[400px] text-tiny"
              items={indicators}
              selectedKeys={selectedIndicator ? [selectedIndicator] : []}
              onChange={(e) => handleIndicator("seriesCode", e.target.value)}
              size="lg"
            >
              {(item) => (
                <SelectItem key={item.seriesCode}>
                  {item.indicatorName}
                </SelectItem>
              )}
            </Select>
          </div>
        )}
      </div>
    </>
  )
}

export default SelectWorld
