import { useHistory } from "react-router";
import { useAuthState } from 'react-firebase-hooks/auth';
import React , { useEffect } from 'react';
import { Estructura } from '../components/Estructura';
import { IniciarSesion}  from '../components/IniciarSesion';

export function Inicio(){

    return(<>

        <Estructura></Estructura>
        
    </>)
}