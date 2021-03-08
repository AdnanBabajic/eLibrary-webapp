import React ,{useState, useEffect}from 'react'
import './LearnMoreDialog.css'

export default function LearnMoreDialog({books, selectedBook, open}) {
  
  return (
  <div className='popup'>
    {books[selectedBook].name}
  </div>
  )}

