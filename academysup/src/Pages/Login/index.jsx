import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './styles.css';
import api from '../../services/api';

export default function Login(){
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    async function login(e){
        e.preventDefault();
        const data = {
            username,
            password
        };

        try{
            const response = await api.post('/auth/login', data);

            localStorage.setItem('id_user', response.data.id_user);
            localStorage.setItem('username', username);
            localStorage.setItem('accessToken', response.data.accessToken);
            localStorage.setItem('email', response.data.email);

           navigate('/posts');
        } catch(err){
            alert('Falha ao logar, verifique seu usuário e senha!')
        }
    };

    return(
        <div className='body-login'>

            <div className='login-container'>
                <h1 className='text-princ'>Login</h1>
                <form onSubmit={login}>
                    <input 
                        type="text" 
                        placeholder="Usuário"
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                    />
                    <input 
                        type="password" 
                        placeholder="Senha"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    />
                    <button className='send-login' type='submit'>Entrar</button>
                </form> 
            </div>

        </div>
    )
}