import React, { useEffect } from "react"
import { Slider } from "@nextui-org/react"

interface SliderValueProps {
  valueParam: string
  handleProperty: (property: string, value: string) => void
}
const SliderValue = ({handleProperty, valueParam}:SliderValueProps) => {


  useEffect(() => {
    handleProperty("value", valueParam);
  }, []);
  
  return (
    <div>
      <div className="text-center mt-3 font-bold text-lg">
        Choose the value baseline
      </div>
      <div>
        <Slider
          label="Value (in %)"
          step={1}
          maxValue={100}
          minValue={0}
          defaultValue={Number(valueParam)}
          onChange={(e) => handleProperty("value", e.toString())}
          className="flex w-[400px ] flex-col justify-center gap-4 mt-5 font-bold"
        />
      </div>
    </div>
  )
}

export default SliderValue
