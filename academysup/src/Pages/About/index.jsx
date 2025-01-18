import React from 'react'

import './styles.css'

import Header from '../Header/index'

export default function About(){
    return(
        <div>
            <Header />
            <div className='box-about'>
                <h1>Sobre</h1>
                <p>
                    Site apenas para praticar back-end, front-end e integração.
                </p>
                <h2>Principais tecnologias utilizadas:</h2>
                <br/>
                <h3>Front-end</h3>
                <ul>
                    <li>HTML</li>
                    <li>CSS</li>
                    <li>React</li>
                </ul>
                <br/>
                <h3>Back-end</h3>
                <ul>
                    <li>Java</li>
                    <li>Spring</li>
                    <li>MySQL</li>
                    <li>JUnit</li>
                    <li>Test-Containers</li>
                </ul>
            </div> 
        </div>
    )
}