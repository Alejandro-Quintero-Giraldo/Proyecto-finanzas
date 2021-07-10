import React, { useState } from 'react';
import { url } from '../App'
import { auth } from './IniciarSesion';

export function mostrarBolsilloId({ bolsilloId }) {

    const dato = fetch(url + '/mostraBolsillo/' + bolsilloId);
    const bolsillo = dato.json();

    return (
        <>



        </>
    );
};

export function actualizarBolsillo({ bolsilloId }) {

    const [nombre, setNombre] = useState();
    const [porcentajeAhorro, setPorcentajeAhorro] = useState([]);

    const actualizar = () => {
        const dato = fetch(url + '/actualizarBolsillo/' + bolsilloId + '/' + nombre + '/' + auth.currentUser.uid + '/' + porcentajeAhorro, { method: 'PUT' })
        dato.then(response => response.json());
        dato.then(data => console.log(data));
    }

    return (
        <>
            <div>
                <form>
                    <label>
                    Nombre:
                    </label>
                    <input type="text" onChange={((e) => setNombre(e.target.value))} name="nombre" value={nombre}/>

                    <label>
                    PorcentajeAhorro:
                    </label>
                    <input type ="number" onChange={(e) => setPorcentajeAhorro(e.target.value)} name="porcentajeAhorro" value={porcentajeAhorro}/>

                    <input type="submit" onClick={actualizar} value="Actualizar bolsillo"/>
                </form>
            </div>
        </>
    )
}

export function eliminarBolsillo({ bolsilloId }) {

    const dato = fetch(url + '/eliminarBolsillo/' + bolsilloId);
    const message = dato.json();
    return (
        <spam>{message}</spam>
    );
}

