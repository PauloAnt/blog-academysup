import React from 'react'
import { Navigate, useNavigate } from 'react-router-dom'
import './styles.css'


export default function Home(){
    const navigate = useNavigate();

    return(
        <div className='home'>
            <h1 className='text-principal'>Seja bem-vindo ao AcademySup</h1>
            <h2 className='text-secundario'>Poste suas dúvidas e interaja com outros usuários!</h2>
            
            <button className="btn-home-register" onClick={() => navigate('/register')}>Clique aqui para começar!</button>
            <button className="btn-home" onClick={() => navigate('/login')}>Já tem conta? Clique aqui</button>
        </div>
    )
}