import { useState } from 'react';
import { url } from '../App';
import { v4 as uuidv4 } from 'uuid';
import { auth } from './IniciarSesion';
import { swal } from 'sweetalert';

export function ingresarDinero({ bolsilloId }) {
    const dineroIngresado = () => {
        swal({
            title: "El dinero ha sido ingresado con éxito",
            icon: "success"
        });
    }

    const [saldo, setSaldo] = useState('');

    const ingresar = () => {

        fetch(url + '/ingresarDinero/' + uuidv4() + '/' + saldo + '/' + bolsilloId + '/' + auth.currentUser.uid, { method: "POST" })
            .then(response => response.json())
            .then(data => console.log(data));
        dineroIngresado;
    }

    return (
        <>
            <div>
                <form>
                    <label>

                    </label>
                    <input type="number" onChange={(e) => setSaldo(e.target.value)} name="saldo" value={saldo} />

                    <input type="submit" onClick={ingresar} value="Confirmar ingreso" />
                </form>
            </div>
        </>
    );
}

export function sacarDinero({ bolsilloId }) {
    const dineroSacado = () => {
        swal({
            title: "El dinero ha sido retirado con éxito",
            icon: "success"
        });
    }

    const [saldo, setSaldo] = useState('');

    const retirar = () => {

        fetch(url + '/sacarDinero/' + uuidv4() + '/' + saldo + '/' + bolsilloId + '/' + auth.currentUser.uid, { method: "POST" })
            .then(response => response.json())
            .then(data => console.log(data));
        dineroSacado;
    }

    return (
        <>
            <div>
                <form>
                    <label>

                    </label>
                    <input type="number" onChange={(e) => setSaldo(e.target.value)} name="saldo" value={saldo} />

                    <input type="submit" onClick={retirar} value="Confirmar ingreso" />
                </form>
            </div>
        </>
    );
}


export function obtenerMovimientosPorBolsillo({ bolsilloId }) {

    const [listaMovimientos, setListaMovimientos] = useState([]);

    const obtener = () => {
        const dato = () => {
            fetch(url + '/mostrarMovimientos/' + bolsilloId)
        };
        setListaMovimientos(dato.json());
    }

    return (
        <>

        </>
    );
}

export function obtenerIngresoMensual({ bolsilloId }) {

    const [mes, setMes] = useState([]);
    const [año, setAño] = useState([]);

    const message='';

    const obtener = () => {
        const dato = fetch(url + '/ingresosMensuales/' + bolsilloId + '/' + mes + '/' + año);
        message = dato.json();
    }

    const content = {
        1: "Enero",
        2: "Febrero",
        3: "Marzo",
        4: "Abril",
        5: "Mayo",
        6: "Junio",
        7: "Julio",
        8: "Agosto",
        9: "Septiembre",
        10: "Octubre",
        11: "Noviembre",
        12: "Diciembre"
    }

    const transformarMes = (value) =>{
        for(let i=1; 12; i++){
            if (value == content.i.value){
                setMes(i);
            }
        }
    }

    const transformarAño = (value) => {
        setAño(value);
    }

    return(
        <>
            <div>
                <form>
                    <label>
                        Mes: 
                    </label>
                    <select onChange={transformarMes(select.target.value)}>
                        <option>Enero</option>
                        <option>Febrero</option>
                        <option>Marzo</option>
                        <option>Abril</option>
                        <option>Mayo</option>
                        <option>Junio</option>
                        <option>Julio</option>
                        <option>Agosto</option>
                        <option>Septiembre</option>
                        <option>Octubre</option>
                        <option>Noviembre</option>
                        <option>Diciembre</option>
                    </select>
                    <label>
                        Año: 
                    </label>
                        <select onChange={transformarAño}>
                            <option> </option>
                            <option>2021</option>
                        </select>
                </form>
            </div>
        </>
    );
}
