import React, { useState } from "react";
import { TextField } from '@material-ui/core';
import './Login.css'

export const Login = ({ onLogin }) => {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");

  const onSubmit = (e) => {
    e.preventDefault();

    if (!name) {
      alert("Please add a name");
      return;
    }

    if (!password) {
      alert("Please add a password");
      return;
    }

    let data = {
      "name" : name,
      "password" : password
    }

    onLogin(name, password);

    setName("");
    setPassword("");
  };
  return (
    <div>
      <form className="login-form" onSubmit={onSubmit}>
        <div className="form-control">
          <TextField id="standard-basic" label="name" type='text' value={name} onChange={(e) => setName(e.target.value)} />
        </div>
        <div className="form-control">
        <TextField id="standard-basic" label="password" type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>

        <input type="submit" value="login" className="btn btn-block" className='submit-btn' />
      </form>
    </div>
  );
};
