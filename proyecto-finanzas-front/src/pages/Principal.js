import { url } from '../App';
import { mostrarBolsilloId } from '../components/Bolsillo';

import React, { useEffect, useState } from 'react';
import { auth } from '../components/IniciarSesion'
import { useAuthState } from 'react-firebase-hooks/auth';
import { swal } from 'sweetalert';


export function Principal() {
    const [listaBolsillos, setListaBolsillos] = useState([]);

    useEffect(() => {
        console.log("");
        obtenerBolsillos();
    })

    const obtenerBolsillos = () => {
        const datos = fetch(url + '/mostrarBolsilloUid/' + auth.currentUser.uid);
        const bolsillos = datos.json();

        setListaBolsillos(bolsillos);
    };

    const [user] = useAuthState(auth);

    const bolsilloEliminado = () => {
        swal({
            title: "El bolsillo ha sido eliminado",
            icon: "success",

        })
    }

    return (<>
        <div class="container-home">
            <div class="card border-info mb-3" style="max-width: 18rem;">
                <div class="card-header">Header</div>
                <div class="card-body">
                    <h5 class="card-title">Info card title</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                </div>
            </div>


        </div>
    </>);

}