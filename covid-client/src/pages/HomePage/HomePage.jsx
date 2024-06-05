
import React, { useState } from 'react';
import CovidTable from '../../components/CovidTable/CovidTable';
import FilterPanel from '../../components/FilterPanel/FilterPanel';
import Header from '../../components/Header/Header';
import CountryDetail from '../../components/CountryDetail/CountryDetail';
import useData from '../../hooks/useData';
import useMigration from '../../hooks/useMigration';

const HomePage = () => {
  const {
    filteredStats,
    searchQuery,
    selectedContinent,
    handleSearchQueryChange,
    handleContinentChange,
    setIsNewDataToFetch,
  } = useData();
  const { exportToJSON, exportToXML, importFromJSON, importFromXML } =
    useMigration(filteredStats, setIsNewDataToFetch);

  const [selectedCountry, setSelectedCountry] = useState(null);

  const handleCountrySelect = (countryData) => {
    console.log('Wybrany kraj:', countryData);
    setSelectedCountry(countryData);
  };

  const handleBack = () => {
    setSelectedCountry(null);
  };

  return (
    <div>
      <Header
        exportToJSON={exportToJSON}
        exportToXML={exportToXML}
        importFromJSON={importFromJSON}
        importFromXML={importFromXML}
      />
      {selectedCountry ? (
        <CountryDetail countryData={selectedCountry} onBack={handleBack} />
      ) : (
        <>
          <FilterPanel
            onQueryChange={handleSearchQueryChange}
            searchQuery={searchQuery}
            selectedContinent={selectedContinent}
            onContinentChange={handleContinentChange}
          />
          <CovidTable filteredStats={filteredStats} onCountrySelect={handleCountrySelect} />
        </>
      )}
    </div>
  );
};

export default HomePage;
