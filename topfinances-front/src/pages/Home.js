import React, { useState } from 'react';
import { url } from '../Router';

function Home(){

    const [arrayBolsillos, setArrayBolsillos] = React.useState([]);

    const obtenerBolsillos = async () => {
        const data = await fetch(url + '/mostrarBolsilloUid/'+ id);
        const bolsillos = await data.json();
        setArrayBolsillos(bolsillos);
    };

    return (
        <>
            <div class="container-home">

                

            </div>
        </>
    );
}

export default Home;