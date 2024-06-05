import React from 'react';
import './CovidTable.css';

const CovidTable = ({ filteredStats, onCountrySelect }) => {
  const handleRowClick = (countryData) => {
    console.log('Row clicked:', countryData);
    onCountrySelect(countryData);
  };

  return (
    <div className="table-container">
      <table className="covid-table">
        <thead>
          <tr>
            <th>Country</th>
            <th>Population</th>
            <th>Total cases</th>
            <th>Total recovered</th>
            <th>Active cases</th>
            <th>Total deaths</th>
          </tr>
        </thead>
        <tbody>
          {filteredStats.map((stat) => (
            <tr key={stat.country.country_name} onClick={() => handleRowClick(stat)}>
              <td>{stat.country.country_name}</td>
              <td>{stat.country_population.toLocaleString()}</td>
              <td>{stat.total_cases.toLocaleString()}</td>
              <td>{stat.total_recovered.toLocaleString()}</td>
              <td>{stat.active_cases.toLocaleString()}</td>
              <td>{stat.total_deaths.toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CovidTable;
