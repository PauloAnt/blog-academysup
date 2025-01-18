import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Login from "./Pages/Login/index";
import Register from './Pages/Register/index'
import Home from "./Pages/Home/index";
import Post from "./Pages/Posts/index"
import Comment from "./Pages/Comments/index"
import MyPosts from "./Pages/MyPosts/index"
import About from "./Pages/About/index"

export default function AppRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" exact element={ <Home /> } />
                <Route path="/login" element={ <Login /> } />
                <Route path="/register" element={ <Register /> } />
                <Route path="/posts" element={ <Post /> } />
                <Route path="/comment/:id" element={ <Comment /> } />
                <Route path="/myposts/:id" element={ <MyPosts /> } />
                <Route path="/about" element={ <About /> } />
            </Routes>
        </BrowserRouter>
    );
}
