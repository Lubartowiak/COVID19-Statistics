import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const useData = () => {
  const [covidStats, setCovidStats] = useState([]);
  const [filteredStats, setFilteredStats] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [selectedContinent, setSelectedContinent] = useState("");
  const [isNewDataToFetch, setIsNewDataToFetch] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCovidStats = async () => {
      try {
        const response = await fetch("http://localhost:8080/stats/all", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });

        if (!response.ok) {
          throw new Error("Invalid credentials");
        }

        const data = await response.json(); console.log(data);
        setCovidStats(data);
        setFilteredStats(data);
        setIsNewDataToFetch(false);
      } catch (e) {
        navigate("/login");
      }
    };

    fetchCovidStats();
  }, [isNewDataToFetch]);

  const handleSearchQueryChange = (e) => {
    setSearchQuery(e.target.value);
  };

  const handleContinentChange = (e) => {
    if (e.target.value === "All") {
      setFilteredStats(covidStats);
    }
    setSelectedContinent(e.target.value);
  };

  useEffect(() => {
    const filterStats = async () => {
      let filteredData = covidStats.filter((stat) => {
        return (
          stat.country.country_name
            .toLowerCase()
            .indexOf(searchQuery.toLowerCase().trim()) > -1
        );
      });
      if (selectedContinent !== "") {
        filteredData = filteredData.filter((stat) => {
          return stat.country.continent.continent_name === selectedContinent;
        });
      }
      setFilteredStats(filteredData);
    };

    filterStats();
  }, [searchQuery, selectedContinent]);

  return {
    filteredStats,
    searchQuery,
    selectedContinent,
    handleSearchQueryChange,
    handleContinentChange,
    setIsNewDataToFetch,
  };
};

export default useData;
