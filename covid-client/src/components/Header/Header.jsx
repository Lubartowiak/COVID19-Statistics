import "./Header.css";
import { useState, useRef } from "react";

const Header = ({
  exportToJSON,
  exportToXML,
  importFromJSON,
  importFromXML,
}) => {
  const [jsonFile, setJsonFile] = useState(null);
  const [xmlFile, setXMLFile] = useState(null);
  const jsonFileInput = useRef(null);
  const xmlFileInput = useRef(null);

  const logout = () => {
    localStorage.removeItem("token");
    window.location.href = "/login";
  };

  const handleImportFromJSON = (e) => {
    importFromJSON(e, jsonFile);
    if (jsonFileInput.current) {
      jsonFileInput.current.value = "";
      setJsonFile(null);
    }
  };

  const handleImportFromXML = (e) => {
    importFromXML(e, xmlFile);
    if (xmlFileInput.current) {
      xmlFileInput.current.value = "";
      setXMLFile(null);
    }
  };

  return (
    <header className="header">
      <nav>
        <ul className="header__nav">
          <li className="header__nav-item" onClick={exportToJSON}>
            Export to JSON
          </li>
          <li className="header__nav-item">
            <form onSubmit={(e) => handleImportFromJSON(e)}>
              <input
                ref={jsonFileInput}
                type="file"
                accept=".json"
                onChange={(e) => setJsonFile(e.target.files[0])}
              />
              <button type="submit">Import from JSON</button>
            </form>
          </li>
          <li className="header__nav-item" onClick={exportToXML}>
            Export to XML
          </li>
          <li className="header__nav-item">
            <form onSubmit={(e) => handleImportFromXML(e)}>
              <input
                ref={xmlFileInput}
                type="file"
                accept=".xml"
                onChange={(e) => setXMLFile(e.target.files[0])}
              />
              <button type="submit">Import from XML</button>
            </form>
          </li>
          <li className="wyloguj" onClick={logout}>
            Logout
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
