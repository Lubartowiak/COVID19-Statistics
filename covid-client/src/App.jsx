import { useState } from "react";
import "./App.css";
import LoginForm from "./components/LoginForm/LoginForm";
import { Route, Routes, Navigate } from "react-router-dom";
import PrivateRoute from "./routes/PrivateRoute";
import HomePage from "./pages/HomePage/HomePage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/home" replace={true} />} />
      <Route path="/login" element={<LoginForm />}></Route>
      <Route
        path="/home"
        element={
          <PrivateRoute>
            <HomePage />
          </PrivateRoute>
        }
      ></Route>
    </Routes>
  );
}

export default App;
