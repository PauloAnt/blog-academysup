import React, { useState, useEffect } from 'react';
import { FaTrash, FaEdit } from "react-icons/fa";

import './styles.css';

import Header from '../Header/index';
import api from '../../services/api';
import { useNavigate } from 'react-router-dom';

export default function MyPosts() {
  const [myPosts, setMyPosts] = useState([]);   
  const [titlePost, setTitlePost] = useState('');
  const [descriptionPost, setDescriptionPost] = useState('');
  const [windowEdit, setWindowEdit] = useState(false);
  const [currentPost, setCurrentPost] = useState(null);

  const username = localStorage.getItem('username');
  const accessToken = localStorage.getItem('accessToken');
  const id_user = localStorage.getItem('id_user');

  const navigate = useNavigate();

  useEffect(() => {
    api.get("/v1/post", {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    })
    .then(response => {
        setMyPosts(response.data.content.filter(e => e.creator.id == parseInt(id_user)));
    })
    .catch(err => {
      console.error('Error posts:', err); 
    });
  }, []); 

  async function delPost(id){
    try {
      await api.delete(`/v1/post/${id}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`
        }
      });

      setMyPosts(prevPosts => prevPosts.filter(post => post.id !== id));
    } catch(err) {
      alert("Error ao deletar, tente novamente.");
    }
  }

  async function editPost(post, title, desc) {
    const data = {
      id: post.id,
      creator: post.creator,
      moment: post.moment,
      title: title,
      description: desc
    };

    try {
      const response = await api.put("v1/post", data, { 
        headers: {
          Authorization: `Bearer ${accessToken}`
        }
      });
      setMyPosts(prevPosts => prevPosts.map(p => p.id === post.id ? response.data : p));
      setWindowEdit(false);
    } catch(err) {
      alert("Ocorreu um error ao editar, tente novamente.");
    }
  }

  const handleButtonWindowEditTrue = (post) => {
    setCurrentPost(post);
    setTitlePost(post.title);
    setDescriptionPost(post.description);
    setWindowEdit(true);
  };

  const handleButtonWindowEditFalse = () => {
    setCurrentPost(null);
    setWindowEdit(false);
  };

  return (
    <div>
      <Header />
      <div className='grid-post'>
        {myPosts.map(post => (
          <div className='mypost' key={post.id}>
            <h2 className='my-title-post'>
              <strong>{post.creator.username}:</strong> {post.title}
            </h2>
            <br/>
            <hr />
            <br/>
            <p className='my-desc-post'>{post.description}</p>
            <br/>
            <hr />
            <br/>
            <div className='post-actions'>
              <button className='btn-post-action' onClick={() => delPost(post.id)}><FaTrash className='icon-react'/></button>
              <button className='btn-post-action' onClick={() => handleButtonWindowEditTrue(post)}><FaEdit className='icon-react'/></button>  
            </div>
          </div>
        ))}
      </div>
      {windowEdit && currentPost && (
        <div className='overlay'>
          <div className='container-edit-post'>
            <div className='box-edit-post'>
              <h2 className='text-princ-edit-post'>Editando</h2>
              <input 
                placeholder='Título da postagem'
                className='title-edit-post' 
                type="text"
                value={titlePost}
                onChange={e => setTitlePost(e.target.value)}
              />
              <textarea 
                placeholder='Digite a sua dúvida aqui'
                className="desc-edit-post" 
                value={descriptionPost} 
                onChange={e => setDescriptionPost(e.target.value)}
              ></textarea>
              <p></p>
              <button className='btn-edit-post' onClick={() => editPost(currentPost, titlePost, descriptionPost)}>Editar</button>
              <button className='btn-close-post' onClick={handleButtonWindowEditFalse}>Fechar</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
