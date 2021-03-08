import React, { useState } from "react";
import Session from 'react-session-api'
import { TextField, Checkbox } from '@material-ui/core';
import './Registration.css'

export const Registration = ({ setLSName, onRegistration }) => {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [admin, setAdmin] = useState(false);

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

    if(onRegistration({ name, password, admin })){

    }
    setLSName(name);

    setName("");
    setPassword("");
    setAdmin(false);
  }

  return (
    <div>
      <form className="login-form" onSubmit={onSubmit}>
        <div className="form-control">
          <TextField id="standard-basic" label="name" type='text' value={name} onChange={(e) => setName(e.target.value)} />
        </div>
        <div className="form-control">
        <TextField id="standard-basic" label="password" type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        <div className="form-control">
        <Checkbox id="standard-basic" label="admin" onChange={(e) => setAdmin(e.target.checked)} />
        </div>
        <input type="submit" value="Register" className="btn btn-block" className='submit-btn' />
      </form>
      {Session.get("isLoggedIn")}
    </div>
  );
};
