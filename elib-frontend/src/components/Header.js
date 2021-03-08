import React from 'react'
import logo from '../logoelib.png'
import './Header.css'

export const Header = () => {
    return (
        <div className="container">
            <div className='logo'>
                <img src={logo} alt=''/>
            </div>
        </div>
    )
}
