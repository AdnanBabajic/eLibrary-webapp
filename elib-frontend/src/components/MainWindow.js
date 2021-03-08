import react, { useState, useEffect } from 'react'
import Session from "react-session-api";
import "./MainWindow.css";
import { Login } from "./Login";
import { Registration } from "./Registration";
import { Books } from "./Books";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

export const MainWindow = ({ onLogin, onRegistration }) => {
    const [LSName, setLSName] = useState(localStorage.getItem('name'));

    // useEffect(() => {
    //   setName(localStorage.getItem('name'));
    //   console.log(name)
    // }, [])

    const logout = () => {
        localStorage.setItem('name', '');
        localStorage.setItem('admin', false);
        console.log("sdada")
    }

  return (
    <div>
      <Router>
        <div>
          <nav>
            <ul> 
              <li>
                <Link to="/">Books</Link>
              </li>
              <li>
                <Link to="/login">Login</Link>
              </li>
              <li>
                <Link to="/register">Register</Link>
              </li>
              <li>
                <Link to="/logout">Logout</Link>
              </li>
            </ul>
          </nav>
          <Switch>
            <Route path="/login"> 
              {LSName != '' ? 'You are already logged in' : <Login onLogin={onLogin} />}
            </Route>
            <Route path="/register">
              {LSName != '' ? 'You are already logged in' : <Registration onRegistration={onRegistration} setLSName={setLSName} /> }
            </Route>
            <Route path="/logout">
              {LSName != '' ? 'Idiot si' : 'You arent logged in'}
            </Route>
            <Route path="/">
            {LSName != '' ? <Books /> : 'you need to log in first'}
            </Route>
          </Switch>
        </div>
      </Router>
    </div>
  );
};
