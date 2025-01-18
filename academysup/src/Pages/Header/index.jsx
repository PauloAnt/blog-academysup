import React from 'react';
import { useNavigate } from 'react-router-dom';

import './styles.css'

export default function Header(){
    const navigate = useNavigate();
    const username = localStorage.getItem('username');

    async function logout(){
        localStorage.clear();
        navigate("/");
    }

    
    return (
        <div>
            <header>
                <div className='div-right-header'>
                    <div className='menu'>
                        <a>Olá, <button className='btn-header-menu'>{username}</button></a>
                        <div className='menu-logout'>
                            <button onClick={() => logout()} className='btn-header-logout margin-left-right-20px'>Logout</button>
                        </div>
                    </div>
                    
                </div>
                <div className='div-header'>
                    <button onClick={() => navigate("/posts")} className='btn-header margin-left-right-20px'>Home</button>
                    <button onClick={() => navigate("/myposts/:id")} className='btn-header margin-left-right-20px'>Minhas postagens</button>
                    <button onClick={() => navigate("/about")} className='btn-header margin-left-right-20px'>Sobre</button>
                </div>
            </header>
        </div>
    );
}
