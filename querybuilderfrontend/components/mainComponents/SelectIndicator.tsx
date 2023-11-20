import React, { useEffect, useState } from "react";
import IndicatorType from "@/types/IndicatorType";
import {Select, SelectItem} from "@nextui-org/react";

interface SelectIndicatorProps {
  indicators : IndicatorType[];
  handleProperty: (property: string, value: string) => void;
}

const SelectWorld = ({
  indicators,
  handleProperty
}: SelectIndicatorProps) => {
  const [selectedTypeFilter, setSelectedTypeFilter] = useState<string>("indicator");

  return (
    <>
      <div >
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
              onChange={(e) => handleProperty("seriesCode", e.target.value)}
              size="lg"
            >
              {(item) => (
                <SelectItem  key={item.seriesCode}>
                  {item.indicatorName}
                </SelectItem >
              )}
            </Select>
          </div>
        )}
      </div>
    </>
  );
};

export default SelectWorld;