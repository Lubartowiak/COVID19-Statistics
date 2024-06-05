import { useEffect, useState } from "react";
import "./FilterPanel.css";

const FilterPanel = ({
  onQueryChange,
  searchQuery,
  selectedContinent,
  onContinentChange,
}) => {
  const [continents, setContinents] = useState([]);

  useEffect(() => {
    const fetchContinents = async () => {
      try {
        const response = await fetch("http://localhost:8080/continent/all", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });

        if (!response.ok) {
          throw new Error("Invalid credentials");
        }

        const data = await response.json();
        setContinents(data);
      } catch (e) {
        console.error(e.message);
      }
    };

    fetchContinents();
  }, []);

  return (
    <div className="filter-panel">
      <input
        type="text"
        onChange={onQueryChange}
        value={searchQuery}
        placeholder="Search by country"
      />

      <select onChange={onContinentChange} value={selectedContinent}>
        <option value="">All</option>
        {continents.map((continent) => {
          return (
            <option
              key={continent.continent_id}
              value={continent.continent_name}
            >
              {continent.continent_name}
            </option>
          );
        })}
      </select>
    </div>
  );
};

export default FilterPanel;
