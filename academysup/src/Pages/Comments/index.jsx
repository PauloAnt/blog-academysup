import React, { useEffect, useState } from 'react';

import api from '../../services/api';

import './styles.css';
import { useParams } from 'react-router-dom';

import Header from '../Header/index'

export default function Comment(){
    const token = localStorage.getItem('accessToken');

    const { id } = useParams()

    const username = localStorage.getItem('username');

    const [comments, setComments] = useState([]);

    useEffect(() => {
        api.get(`/v1/comment`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then(response => (
            
            setComments(response.data.filter(c => c.post.id == parseInt(id)))
            
        ))
        
    })
    
    return (
        <div>
            <Header />
            <h2 className='princ-text-comment'>Comentários: {comments.length}</h2>
            <br />
            <ul className='list-comment'>
                {comments.map(c => (
                    
                    <li className='item-comment'>
                        <p className='name-comment'>{c.creator.username} comentou:</p>
                        <br/>
                        <hr/>
                        <br/>
                        <p className='desc-comment'>{c.description}</p>
                        <br/>
                    </li>
                ))}
            </ul>
        </div>
    );
}