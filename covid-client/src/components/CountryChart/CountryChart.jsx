
import React from 'react';
import { Bar } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const CountryChart = ({ countryData }) => {
  const data = {
    labels: ['Total Cases', 'Total Deaths', 'Total Recovered', 'Active Cases'],
    datasets: [
      {
        label: countryData.country.country_name,
        data: [
          countryData.total_cases,
          countryData.total_deaths,
          countryData.total_recovered,
          countryData.active_cases,
        ],
        backgroundColor: [
          'rgba(75, 192, 192, 0.2)',
          'rgba(255, 99, 132, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(255, 206, 86, 0.2)',
        ],
        borderColor: [
          'rgba(75, 192, 192, 1)',
          'rgba(255, 99, 132, 1)',
          'rgba(153, 102, 255, 1)',
          'rgba(255, 206, 86, 1)',
        ],
        borderWidth: 1,
      },
    ],
  };

  return (
    <div>
      <h2>{countryData.country.country_name}</h2>
      <Bar data={data} />
    </div>
  );
};

export default CountryChart;
