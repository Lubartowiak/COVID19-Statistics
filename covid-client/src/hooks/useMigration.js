import { js2xml } from "xml-js";
import useData from "./useData";

const useMigration = (filteredStats, setIsNewDataToFetch) => {
  const convertToProperObject = (data) => {
    return data.map((item) => {
      return {
        country_name: item.country.country_name,
        continent_name: item.country.continent.continent_name,
        country_population: item.country_population,
        total_cases: item.total_cases,
        total_deaths: item.total_deaths,
        total_recovered: item.total_recovered,
        active_cases: item.active_cases,
        birth_stats: item.country.birthStats.map((stat) => {
          return {
            year: stat.year,
            births: stat.births,
          };
        }),
      };
    });
  };

  const convertToXMLFriendly = (data) => {
    return {
      covid_data: {
        country: data.map((item) => {
          return {
            ...item,
            birth_stats: {
              birth_stat: item.birth_stats.map((stat) => {
                return {
                  year: stat.year,
                  births: stat.births,
                };
              }),
            },
          };
        }),
      },
    };
  };

  const exportToJSON = () => {
    const outputFormat = convertToProperObject(filteredStats);
    const json = JSON.stringify(outputFormat);
    const blob = new Blob([json], { type: "application/json" });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = "covid-data.json";
    link.click();
  };

  const exportToXML = () => {
    const json = convertToXMLFriendly(convertToProperObject(filteredStats));
    const xml = js2xml(json, { compact: true });
    const blob = new Blob([xml], { type: "application/xml" });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = "covid-data.xml";
    link.click();
  };

  const importFromJSON = (e, jsonFile) => {
    e.preventDefault();
    if (jsonFile) {
      const reader = new FileReader();
      reader.onload = async (event) => {
        try {
          const fileData = JSON.parse(event.target.result);
          const response = await fetch("http://localhost:8080/stats/import", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
            body: JSON.stringify(fileData),
          });

          if (!response.ok) {
            let error = await response.json();
            throw new Error(error.message);
          }

          let data = await response.json();
          alert(data.message);
          setIsNewDataToFetch(true);
        } catch (e) {
          alert(e.message);
        }
      };
      reader.readAsText(jsonFile);
    }
  };

  const importFromXML = (e, xmlFile) => {
    e.preventDefault();
    if (xmlFile) {
      const reader = new FileReader();
      reader.onload = async (event) => {
        try {
          const fileData = event.target.result;
          const response = await fetch(
            "http://localhost:8080/stats/importXML",
            {
              method: "POST",
              headers: {
                "Content-Type": "application/xml",
                Authorization: `Bearer ${localStorage.getItem("token")}`,
              },
              body: fileData,
            }
          );

          if (!response.ok) {
            let error = await response.json();
            throw new Error(error.message);
          }

          let data = await response.json();
          alert(data.message);
          setIsNewDataToFetch(true);
        } catch (e) {
          alert(e.message);
        }
      };
      reader.readAsText(xmlFile);
    }
  };

  return { exportToJSON, exportToXML, importFromJSON, importFromXML };
};

export default useMigration;
