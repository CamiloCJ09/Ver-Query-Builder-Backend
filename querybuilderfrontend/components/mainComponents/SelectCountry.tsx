import React, { useEffect, useState } from "react"
import CountryType from "@/types/CountryType"
import { Select, SelectItem } from "@nextui-org/react"

interface SelectCountryProps {
  countries: CountryType[]
  countryParam: string
  handleProperty: (property: string, value: string) => void
}

const SelectCountry = ({ countries, handleProperty , countryParam}: SelectCountryProps) => {
  const [selectedTypeFilter, setSelectedTypeFilter] =useState<string>("country")

  const [selectedCountry, setSelectedCountry] = useState<string>(countryParam)

  const handleCountry = (property: string, value: string) => {
    setSelectedCountry(value)
    handleProperty(property, value)
  }
  useEffect(() => {
    handleProperty("countryCode", countryParam)
  }, [])
  return (
    <>
      <div>
        <div className="text-center mt-3 font-bold text-lg">
          Choose the country to filter
        </div>

        {selectedTypeFilter === "country" && (
          <div className="flex w-full flex-row justify-center md:flex-nowrap gap-4 mt-5">
            <Select
              label="Select a country"
              placeholder="Search an country"
              className="max-w-xs"
              items={countries}
              selectedKeys={selectedCountry ? [selectedCountry] : []}
              onChange={(e) => handleCountry("countryCode", e.target.value)}
              size="lg"
            >
              {(item) => (
                <SelectItem key={item.countryCode}>
                  {item.countryName}
                </SelectItem>
              )}
            </Select>
          </div>
        )}
      </div>
    </>
  )
}

export default SelectCountry
