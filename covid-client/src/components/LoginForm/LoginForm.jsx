import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./LoginForm.css";

const LoginForm = () => {
  const [formData, setFormData] = useState({});
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(
        "http://localhost:8080/api/auth/authenticate",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        }
      );

      if (!response.ok) {
        throw new Error("Invalid credentials");
      }

      const { token } = await response.json();
      localStorage.setItem("token", token);
      navigate("/home");
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <div className="login-form-container">
      <div className="login-form">
        <h1>Login</h1>
        <form onSubmit={(e) => handleSubmit(e)}>
          <input
            type="text"
            name="username"
            onChange={(e) => handleChange(e)}
            placeholder="Username"
          />
          <input
            type="password"
            name="password"
            onChange={(e) => handleChange(e)}
            placeholder="Password"
          />
          <button className="button1">Login</button>
        </form>
      </div>
    </div>
  );
};

export default LoginForm;
