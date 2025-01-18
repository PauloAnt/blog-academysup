import React, { useEffect, useState } from 'react';
import { FaComment } from "react-icons/fa";
import { IoMdSend } from "react-icons/io";
import api from '../../services/api';
import './styles.css';
import Header from '../Header/index';
import { useNavigate } from 'react-router-dom';

export default function Post(){
    const [posts, setPosts] = useState([]);
    const [descriptions, setDescriptions] = useState({});
    const [titleNewPost, titleSetNewPost] = useState('');
    const [descriptionNewPost, descriptionSetNewPost] = useState('');

    const username = localStorage.getItem('username');
    const token = localStorage.getItem('accessToken');
    const id_user = localStorage.getItem('id_user');

    const navigate = useNavigate();

    useEffect(() => {
        api.get("/v1/post", {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then(response => {
            setPosts(response.data.content);

            const initialDescriptions = {};
            response.data.content.forEach(post => {
                initialDescriptions[post.id] = '';
            });
            setDescriptions(initialDescriptions);
        }).catch(error => {
            console.error('Error posts:', error);
        });
    }, []);

    async function sendNewPost(){
        const data = {
            userId: id_user,
            title: titleNewPost,
            description: descriptionNewPost,
            moment: new Date().toISOString()
        }

        try{
            api.post('/v1/post', data, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            titleSetNewPost('');
            descriptionSetNewPost('');
            window.location.reload();

        } catch(err){
            alert("Ocorreu um error, tente novamente.")
            console.log(err);
        }

        // {
        //     "id": 1,
        //     "userId": 123,
        //     "title": "My First Post",
        //     "description": "This is the description of my first post.",
        //     "moment": "2024-07-10T10:15:30Z"
        //   }
    }

    async function sendComment(post) {
        const data = {
            userId: id_user,
            postId: post.id,
            description: descriptions[post.id],
            moment: new Date().toISOString()
        };

        try {
            await api.post("/v1/comment", data, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            setDescriptions({
                ...descriptions,
                [post.id]: ''
            });
        } catch (err) {
            alert("Ocorreu um erro ao enviar o comentário, por favor, tente novamente.");
            console.error('Error sending comment:', err);
        }
    }

    const handleDescriptionChange = (postId, value) => {
        setDescriptions({
            ...descriptions,
            [postId]: value
        });
    };
    const sortedPosts = posts.sort((a, b) => new Date(b.moment) - new Date(a.moment));
    return (
        <div>
            <Header />
            <br/>   
            <br/>
            <br/>
            <div className='container-send-post'>
                <div className='box-send-post'>
                    <h2 className='text-princ-send-post'>Faça sua postagem</h2>
                    <input 
                    placeholder='Título da postagem'
                        className='title-send-post' 
                        type="text"
                        value={titleNewPost}
                        onChange={e => titleSetNewPost(e.target.value)}
                    />
                    <textarea 
                        placeholder='Digite a sua dúvida aqui'
                        className="new-post" 
                        value={descriptionNewPost} 
                        onChange={e => descriptionSetNewPost(e.target.value)}
                    ></textarea>
                    <button className='btn-send-post' onClick={sendNewPost}>Criar postagem</button>
                </div>
            </div>
            <br/>
            <br/>
            <h1 className='text-post-princ'>Posts recentes</h1>

            <ul className='posts'>
                {posts.map(post => (
                    <li className='post' key={post.id}>
                        <br/>
                        <h2 className='title-post'><strong>{post.creator.username}:</strong> {post.title}</h2>
                        <br/>
                        <hr/>
                        <br/>
                        <p className='desc-post'>{post.description}</p>
                        <br/>
                        <hr/>
                        <br/>
                        <p className='answer-text'>Responder:</p>
                        <textarea 
                            placeholder="Faça seu comentário aqui"
                            className="comment-text" 
                            cols="30" 
                            rows="10"
                            value={descriptions[post.id]}
                            onChange={e => handleDescriptionChange(post.id, e.target.value)} 
                        ></textarea>
                        
                        <p className='text-align-left'><button className='btn-send text-align-left' onClick={() => sendComment(post)}><IoMdSend className='icon-react' /></button></p>
                        
                        <p className='text-align-center'><button className='btn-see-comment text-align-center' 
                            onClick={() => navigate(`/comment/${post.id}`)}>Ver comentários <FaComment /></button></p>
                    </li>
                ))}
            </ul>
        </div>
    );
}
