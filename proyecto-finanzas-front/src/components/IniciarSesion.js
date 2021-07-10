import firebase from 'firebase/app';
import 'firebase/auth';
import { img } from '../assets/simbolo-de-google.svg';
import { crearUsuario } from '../components/Usuario';

export const auth = firebase.auth();

export function IniciarSesion(){
    const signInWithGoogle = () => {
        const provider = new firebase.auth.GoogleAuthProvider();
        auth.signInWithPopup(provider);
        <crearUsuario/>
    }

    return (
        <button class="button-login" onClick={signInWithGoogle}>
        <img src='../assets/simbolo-de-google.svg'/>
            Iniciar Sesión con Google
        </button>
    );
}
