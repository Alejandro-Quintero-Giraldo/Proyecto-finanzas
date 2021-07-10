import { useState } from 'react';
import { url} from '../App';
import { auth } from '../components/IniciarSesion';
import { v4 as uuidv4 } from 'uuid';

export function crearBolsillo(){

    const [id, setId] = useState('');
    const [nombre, setNombre] = useState('');
    const [uid, setUid] = useState('');
    const [porcentajeAhorro, setPorcentajeAhorro] = useState("");
    
    setUid(auth.currentUser.uid);
    setId(uuidv4());

    const crear = () => {
        fetch(url +'/creaBolsillo/'+ id + '/'+ nombre + '/' + uid + '/' + porcentajeAhorro, {method:"POST"})
        .then(response => response.json())
        .then(data => console.log(data)); 
    }

    return (
        
        <div>
            <form>
                <label>
                    Nombre: 
                </label>
                <input type="text" onChange={(e) => setNombre(e.target.value)} name="nombre" value={nombre} />

                <label>
                    Porcentaje de ahorro: 
                </label>
                <input type="number" onChange={(e) => setPorcentajeAhorro(e.target.value)} name="porcentajeAhorro" value={porcentajeAhorro}/>

                <input type="submit" onClick={crear} value="Crear bolsillo"></input> 
            </form>

        </div>

    );
}