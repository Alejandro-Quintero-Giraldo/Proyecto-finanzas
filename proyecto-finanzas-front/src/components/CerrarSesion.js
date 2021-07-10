import { auth } from './IniciarSesion';

export function CerrarSesion(){
    auth.signOut();
    window.location.href = "/";
}