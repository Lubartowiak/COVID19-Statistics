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
import './CountryDetail.css';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const CountryDetail = ({ countryData, onBack }) => {
  if (!countryData) return null;

  
  const labels = countryData.country.birthStats.map(stat => stat.year);
  const birthData = countryData.country.birthStats.map(stat => stat.births);

  const data = {
    labels: labels,
    datasets: [
      {
        label: `${countryData.country.country_name} Births (in thousand)`,
        data: birthData,
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 1,
      },
    ],
  };

  return (
    <div className="country-detail">
      <button className="back-button" onClick={onBack}>Back</button>
      <h2>{countryData.country.country_name}</h2>
      <Bar data={data} />
    </div>
  );
};

export default CountryDetail;
