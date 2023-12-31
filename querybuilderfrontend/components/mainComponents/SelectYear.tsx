import React, { useEffect } from "react";

import { Autocomplete, AutocompleteItem, Button } from "@nextui-org/react";
import { dataFill } from "@/app/data/dataFill";
import {Select, SelectItem} from "@nextui-org/react";


interface SelectYearProps {
  yearParam: string;
  handleProperty: (property: string, value: string) => void;
}
const SelectYear = ({handleProperty, yearParam}:SelectYearProps) => {

  const [selectedYear, setSelectedYear] = React.useState<string>(yearParam);

  const handleYear = (property: string, value: string) => {
    setSelectedYear(value);
    handleProperty(property, value);
  }
  useEffect(() => {
    handleProperty("year", yearParam);
  }, []);

  return (
    <div>
      <div className="text-center mt-3 font-bold text-lg">
        Choose the year range
      </div>
      <div>
      <Select
              label="Select a year baseline"
              placeholder="Select a year baseline"
              className="max-w-xs"
              items={dataFill.years}
              
              selectedKeys={selectedYear ? [selectedYear] : []}
              onChange={(e) => handleYear("year", e.target.value)}
              size="lg"
            >
              {(item) => (
                <SelectItem key={item.id}>
                  {item.name}
                </SelectItem>
              )}
            </Select>
      </div>
    </div>
  );
};

export default SelectYear;