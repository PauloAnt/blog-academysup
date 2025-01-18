import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './styles.css';
import api from '../../services/api';

export default function Register() {
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const roles = ["COMMON_USER"];

    const navigate = useNavigate();

    async function register(e) {
        e.preventDefault();

        const data = {
            email,
            username,
            password,
            roles
        };

        // console.log("Enviando dados:", data); 

        try {
            const response = await api.post('/auth/register', data);

            // console.log("Resposta da API:", response);  // Adicione um log para inspecionar a resposta da API
            
            navigate("/");
        } catch (err) {
            // console.error("Erro ao registrar:", err); 
            alert("Já existe uma conta com esses dados.");
        }
    }

    return (
        <div className='body-login'>
            <div className='login-container'>
                <h1 className='text-princ'>Register</h1>
                <form onSubmit={register}>
                    <input 
                        type="text" 
                        placeholder="Usuário"
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                        required
                    />
                    <input 
                        type="text" 
                        placeholder="Email"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                        required
                    />
                    <input 
                        type="password" 
                        placeholder="Senha"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        required
                    />

                    <button className='send-register' type='submit'>Registrar</button>
                </form>
            </div>
        </div>
    );
}
