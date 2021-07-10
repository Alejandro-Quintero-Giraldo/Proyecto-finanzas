import { url } from '../App';
import { auth } from '../components/IniciarSesion';
import { Principal } from '../pages/Principal'


export function crearUsuario() {

    const usuario = {
        uid: auth.currentUser.uid,
        nombre: auth.currentUser.displayName,
        email: auth.currentUser.email
    }

    fetch(url + '/crearUsuario/' + usuario.uid +
        '/' + usuario.nombre +
        '/' + usuario.email,
        { method: "POST" })
        .then(response => response.json())
        .then(data => console.log(data));
    if(usuario){
        window.location(Principal);
    }

}