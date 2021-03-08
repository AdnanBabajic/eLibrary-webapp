import react, { useState } from 'react'
import Session from 'react-session-api'
import './App.css';
import { Header } from './components/Header'
import { MainWindow } from './components/MainWindow'

function App() {
  const onLogin = async(loginData) => {
    const res = await fetch('http://localhost:8080/elibrary/user/login', {
      method: 'POST',
      headers: {
        'Content-type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Headers': '*',
        'Access-Control-Allow-Credentials': 'true',
        'Access-Control-Allow-Methods': 'GET, HEAD, OPTIONS, POST, PUT',
        'Access-Control-Allow-Origin': 'http://localhost:3000/',
      },
      body: JSON.stringify(loginData)
    })
    if(res.ok) {
      const data = await res.json();
      localStorage.setItem('id', data.id);
      localStorage.setItem('name', data.name);
      localStorage.setItem('admin', data.admin);
    }
  }

  const onRegistration = async(registrationData) => {
    const res = await fetch('http://localhost:8080/elibrary/user/register', {
      method: 'POST',
      headers: {
        'Content-type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Headers': '*',
        'Access-Control-Allow-Credentials': 'true',
        'Access-Control-Allow-Methods': 'GET, HEAD, OPTIONS, POST, PUT',
        'Access-Control-Allow-Origin': 'http://localhost:3000/',
      },
      body: JSON.stringify(registrationData)
    })
    if(res.ok) {
      const data = await res.json();
      localStorage.setItem('id', data.id);
      localStorage.setItem('name', data.name);
      localStorage.setItem('admin', data.admin);
    }
  }

  return (
    <>
      <Header />
      <div className='body'>
        <MainWindow onLogin={onLogin} onRegistration={onRegistration} />
      </div>
    </>
  );
}

export default App;
