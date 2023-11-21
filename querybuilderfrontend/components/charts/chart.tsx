import GraphType from "@/types/GraphType"
import React, { useRef, useEffect, use } from "react"
import Chart from "chart.js/auto"

interface GraphProps {
  queryData: GraphType
}

const ChartComponent = ({ queryData }: GraphProps) => {
  const data = queryData
  const chartRef = useRef(null)

  useEffect(() => {
    if (chartRef.current) {
      const currentChart = chartRef.current
      var ctx = chartRef.current

      var existingChart = Chart.getChart(ctx)

      if (existingChart) {
        existingChart.destroy()
      }

      new Chart(currentChart, {
        type: "bar",
        data: {
          labels: data?.years,
          datasets: [
            {
              label: data?.indicator,
              data: data?.values,
              borderColor: "rgb(65, 75, 220)",
              backgroundColor: "rgb(65, 75, 220)",
            },
          ],
        },
      })
    }
  }, [data])

  return (
    <div>
      <canvas ref={chartRef} width="500" height="500" />
    </div>
  )
}
export default ChartComponent
